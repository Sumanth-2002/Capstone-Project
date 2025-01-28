package com.ust.Billing_Service.service;

import com.ust.Billing_Service.dto.StoreSaledDto;
import com.ust.Billing_Service.dto.StoreYearlyDto;
import com.ust.Billing_Service.repository.BillingRepository;
import com.ust.Billing_Service.repository.ProductBilledRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class MetricsService {
    @Autowired
    private BillingRepository billingRepository;

    @Autowired
    private ProductBilledRepository productBilledRepository;

    private static final List<String> MONTH_NAMES = Arrays.asList(
            "January", "February", "March", "April", "May", "June",
            "July", "August", "September", "October", "November", "December"
    );
    public List<StoreSaledDto> getStoresSales(String companyId){
        return  billingRepository.getSales(companyId);
    }
    public Map<String,Double> getTotalSales(String companyId){
        Map<String,Double> totalSales = new HashMap<>();
        totalSales.put("TotalSales",billingRepository.getTotalSales(companyId));
        return totalSales;
    }
    public List<StoreYearlyDto> getYearlyMetrics(String storeId, Integer year){
        List<Object[]> results = billingRepository.getStoreSalesYearly(storeId,year);
        List<StoreYearlyDto> allMonths = MONTH_NAMES.stream()
                .map(month -> {
                    StoreYearlyDto dto = new StoreYearlyDto();
                    dto.setMonth(month);
                    dto.setTotalSale(0);
                    return dto;
                }).collect(Collectors.toList());
        for (Object[] row : results) {
            int monthNumber = (int) row[0];
            double totalSale = ((Number) row[1]).doubleValue();

            StoreYearlyDto dto = allMonths.get(monthNumber - 1);
            dto.setTotalSale(totalSale);
        }
        return allMonths;
    }
    public List<Map<String,Object>> getProductSalesByStore(String storeId){
        Map<String,Object> response = new HashMap<>();
        List<Map<String,Object>> responseList = new ArrayList<>();
        for(Object[] obj :productBilledRepository.getProductsByStoreWise(storeId)){
            response.put("productName",obj[0]);
            response.put("quantity",obj[1]);
            responseList.add(response);
        }
        return responseList ;
    }
    public List<Map<String, Object>> getCustomerForStore(String storeId, Integer year) {

        List<Object[]> customers = billingRepository.getCustomerForStore(storeId, year);
        Map<String, Map<String, Object>> customersStoreMap = MONTH_NAMES.stream()
                .collect(Collectors.toMap(
                        month -> month,
                        month -> {
                            Map<String, Object> monthData = new HashMap<>();
                            monthData.put("month", month);
                            monthData.put("customerCount", 0); // Default value
                            return monthData;
                        }
                ));

        for (Object[] record : customers) {
            // Extract data from the query result (assuming the query returns month as Integer and customerCount as Long)
            Integer monthIndex = (Integer) record[0];
            Long customerCount = (Long) record[1];

            // Get the corresponding month name and update the map
            String monthName = MONTH_NAMES.get(monthIndex - 1); // Months are 1-based in SQL
            customersStoreMap.get(monthName).put("customerCount", customerCount);
        }

        // Convert the map values to a list and return
        return new ArrayList<>(customersStoreMap.values());
    }

}
