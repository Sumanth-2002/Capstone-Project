package com.ust.Billing_Service.service;

import com.ust.Billing_Service.dto.StoreSaledDto;
import com.ust.Billing_Service.repository.BillingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class MetricsService {
    @Autowired
    private BillingRepository billingRepository;

    public List<StoreSaledDto> getStoresSales(String companyId){
        return  billingRepository.getSales(companyId);
    }
    public Map<String,Double> getTotalSales(String companyId){
        Map<String,Double> totalSales = new HashMap<>();
        totalSales.put("TotalSales",billingRepository.getTotalSales(companyId));
        return totalSales;
    }
}
