package com.ust.Billing_Service.service;

import com.ust.Billing_Service.dto.ProductDto;
import com.ust.Billing_Service.entity.Billing;
import com.ust.Billing_Service.entity.Customer;
import com.ust.Billing_Service.entity.ProductBilled;
import com.ust.Billing_Service.repository.CustomerRepository;
import com.ust.Billing_Service.repository.ProductBilledRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import org.xhtmlrenderer.pdf.ITextRenderer;

import java.io.ByteArrayOutputStream;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class InvoiceService {

    @Autowired
    private BillingService billingService;

    @Autowired
    private ProductBilledRepository productBilledRepository;

    @Autowired
    private TemplateEngine templateEngine;

    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private WebClient.Builder webClientBuilder;

    /**
     * Generate the HTML content for the invoice using Thymeleaf template.
     */
    public String generateInvoiceHtml(String billingId) {
        Billing billing = billingService.getBillingByid(billingId);
        Map<String,Object> map = new HashMap<>();
        map = getStoreDetails(billing.getStoreId());
        Map<String,Object> company  = new HashMap<>();
        company = WebClient.builder()
                .baseUrl("http://localhost:9090")
                .build()
                .get()
                .uri("/api/company/getCompanyDetails/"+map.get("companyId")) // Direct URI without query parameters
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<Map<String,Object>>() {})
                .block();

        Context context = new Context();
        context.setVariable("companyName",company.get("name") );
        context.setVariable("GSTIN",company.get("GSTIN") );
        context.setVariable("billingId", billing.getBillingId());
        context.setVariable("customerName", billing.getCustomerName());
//        context.setVariable("customerContact",getCustomerById(billing.getCustomerId()).getContact());
        context.setVariable("orderDate", LocalDate.now().toString()); // Current date

        List<ProductBilled> products = productBilledRepository.findAllByBillingId(billingId);
        List<ProductDto> productDTOs = products.stream()
                .map(product -> new ProductDto(
                        product.getProductName(),
                        product.getProductId(),
                        product.getPrice(),
                        product.getQuantity(),
                        product.getPrice() * product.getQuantity() // Calculate subtotal
                ))
                .toList();

        context.setVariable("products", productDTOs);

        // Calculate totals
        double subtotal = productDTOs.stream().mapToDouble(ProductDto::getSubtotal).sum();
        double taxPercentage = 12; // Example tax percentage
        double taxAmount = subtotal * (taxPercentage / 100);
        double totalAmount = subtotal + taxAmount;

        context.setVariable("subtotal", subtotal);
        context.setVariable("taxPercentage", taxPercentage);
        context.setVariable("taxAmount", taxAmount);
        context.setVariable("totalAmount", totalAmount);

        context.setVariable("storeName", map.get("storeName"));
        context.setVariable("storeAddress", map.get("storeAddress"));
        context.setVariable("region",map.get("region"));
        // Render the template and return the HTML content
        return templateEngine.process("invoice", context);
    }

    /**
     * Generate PDF from the provided HTML content.
     */
    public byte[] generatePdf(String htmlContent) throws Exception {
        try (ByteArrayOutputStream outputStream = new ByteArrayOutputStream()) {
            ITextRenderer renderer = new ITextRenderer();
            renderer.setDocumentFromString(htmlContent);
            renderer.layout();
            renderer.createPDF(outputStream);
            return outputStream.toByteArray();
        }
    }

    public Map<String,Object> getStoreDetails(String storeId){
        Map<String,Object> map = new HashMap<>();
         map = WebClient.builder()
                .baseUrl("http://localhost:9092")
                .build()
                .get()
                .uri("/api/stores/getStore/"+storeId) // Direct URI without query parameters
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<Map<String,Object>>() {})
                .block();
         return map;
    }

    public Customer getCustomerById(String customerId){
        return customerRepository.findById(customerId).get();
    }

}
