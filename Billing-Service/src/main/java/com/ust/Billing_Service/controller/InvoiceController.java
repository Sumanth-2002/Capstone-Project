//package com.ust.Billing_Service.controller;
//
//import com.ust.Billing_Service.entity.Billing;
//import com.ust.Billing_Service.entity.ProductBilled;
//import com.ust.Billing_Service.service.BillingService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//
//import java.time.LocalDate;
//
//@Controller
//public class InvoiceController {
//
//    @Autowired
//    private BillingService billingService;
//
//    @GetMapping("/generate-invoice/{billingId}")
//    public String generateInvoice(@PathVariable String billingId, Model model) {
//        Billing billing = billingService.getBillingByid(billingId);
//        model.addAttribute("companyName", "Your Company Name");
//        model.addAttribute("billingId", billing.getBillingId());
//        model.addAttribute("customerName", billing.getCustomerName());
//        model.addAttribute("orderDate", LocalDate.now().toString()); // Current date
//
//        // Add product details
////        model.addAttribute("products", billing.getProducts());
//
//        // Calculate totals
//        double subtotal = 0;
//        double taxPercentage = 5.0; // Example tax percentage
//        double taxAmount = 0;
//        for (ProductBilled product : billing.getProducts()) {
//            subtotal += product.getTotalPrice();
//        }
//        taxAmount = subtotal * taxPercentage / 100;
//        double totalAmount = subtotal + taxAmount;
//
//        model.addAttribute("subtotal", subtotal);
//        model.addAttribute("taxPercentage", taxPercentage);
//        model.addAttribute("taxAmount", taxAmount);
//        model.addAttribute("totalAmount", totalAmount);
//
//        // Add store info
//        model.addAttribute("storeName", "Your Store");
//        model.addAttribute("region", "Region Name");
//        model.addAttribute("storeAddress", "Store Address");
//
//        return "invoice";
//    }
//}
