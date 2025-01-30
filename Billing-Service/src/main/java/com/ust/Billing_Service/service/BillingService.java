package com.ust.Billing_Service.service;

import com.ust.Billing_Service.dto.BillingDto;
import com.ust.Billing_Service.dto.SalesDto;
import com.ust.Billing_Service.dto.UpdateProductDto;
import com.ust.Billing_Service.entity.Billing;
import com.ust.Billing_Service.entity.Customer;
import com.ust.Billing_Service.entity.ProductBilled;
import com.ust.Billing_Service.generator.CustomIdGenerator;
import com.ust.Billing_Service.repository.BillingRepository;
import com.ust.Billing_Service.repository.CustomerRepository;
import com.ust.Billing_Service.repository.ProductBilledRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.*;

@Service
public class BillingService {
    @Autowired
    private BillingRepository billingRepository;

    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private ProductBilledRepository productBilledRepository;
    @Autowired
    private WebClient.Builder webClientBuilder;

    public String addBillingData(BillingDto billingDto) {
        Double totalPrice = 0.0;
        Billing billing = new Billing();
        billing.setStoreId(billingDto.getStoreId());
        billing.setStoreName(billingDto.getStoreName());
        billing.setCustomerId(billingDto.getCustomerId());
        billing.setCustomerName(billingDto.getCustomerName());
        billing.setSalesRepId(billingDto.getSalesRepId());
//        billing.setTotalPrice(billingDto.getTotalPrice());
        billingRepository.save(billing);
        for(ProductBilled productBilled : billingDto.getProductBilledList()) {
            productBilled.setBillingId(billing.getBillingId());
            UpdateProductDto updateProductDto = new UpdateProductDto();
            updateProductDto.setProductId(productBilled.getProductId());
            updateProductDto.setProductName(productBilled.getProductName());
            updateProductDto.setQuantity(productBilled.getQuantity());
            updateProductDto.setStoreId(billing.getStoreId());
            updateProductDto.setStoreName(billing.getStoreName());
            String object = WebClient.builder()
                    .baseUrl("http://localhost:9092")
                    .build()
                    .put()
                    .uri("/api/stores/update-stock") // Direct URI without query parameters
                    .bodyValue(updateProductDto) // Attach the DTO as the body
                    .retrieve()
                    .bodyToMono(new ParameterizedTypeReference<String>() {})
                    .block();
            String productTd = productBilled.getProductId();
            Map<String,Object> obj = WebClient.builder()
                    .baseUrl("http://localhost:9091")
                    .build()
                    .get()
                    .uri("/api/products/getproductById/"+productTd) // Direct URI without query parameters
                  // Attach the DTO as the body
                    .retrieve()
                    .bodyToMono(new ParameterizedTypeReference<Map<String,Object>>() {})
                    .block();
            double sellingPrice = Double.parseDouble(obj.get("selling_Price").toString());
            productBilled.setPrice(sellingPrice);

            int quantity = productBilled.getQuantity();
            double totalPrices = quantity * sellingPrice;

            productBilled.setTotalPrice(totalPrices);
            productBilledRepository.save(productBilled);
            totalPrice+=productBilled.getTotalPrice();

        }
        System.out.println(totalPrice);
        billing.setTotalPrice(totalPrice);
        billingRepository.save(billing);

        return "Success";
    }

    public String addCustomerData(Customer customer){
        customerRepository.save(customer);
        return  customer.getCustomerId();
    }
    public Billing getBillingByid(String billingId){
        Billing billing = billingRepository.findById(billingId).get();
        System.out.println(billing);
        return billing;

    }

    public  List<Map<String,Object>> getLeaderboard(String storeId){
        List<Object[]> results = billingRepository.getLeaderboard(storeId);
        List<SalesDto> salesDtos = new ArrayList<>();

        List<Map<String,Object>> leaderboard = new ArrayList<>();
        for (Object[] result : results) {
            salesDtos.add(new SalesDto(
                    (String) result[0], // salesRepId
                    (String) result[1], // salesRepName
                    ((Double) result[2]).doubleValue() // noofSales
            ));
        }
        for(Object[] result : results) {
            Map<String,Object> data = new HashMap<>();
            data.put("salesRepId", (String) result[0]);
            data.put("salesRepName", (String) result[1]);
            data.put("TotalSale",result[2]);
            leaderboard.add(data);
        }
        return leaderboard;
    }
}
