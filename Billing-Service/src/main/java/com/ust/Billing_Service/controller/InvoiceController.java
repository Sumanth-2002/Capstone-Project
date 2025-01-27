package com.ust.Billing_Service.controller;

import com.lowagie.text.DocumentException;
import com.ust.Billing_Service.dto.ProductDto;
import com.ust.Billing_Service.entity.Billing;
import com.ust.Billing_Service.entity.ProductBilled;
import com.ust.Billing_Service.repository.ProductBilledRepository;
import com.ust.Billing_Service.service.BillingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import org.xhtmlrenderer.pdf.ITextRenderer;
import org.xhtmlrenderer.simple.PDFRenderer;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.StringWriter;
import java.time.LocalDate;
import java.util.List;

@Controller
public class InvoiceController {

    @Autowired
    private BillingService billingService;

    @Autowired
    private ProductBilledRepository productBilledRepository;

    @Autowired
    private TemplateEngine templateEngine;


    @GetMapping("/generate-invoice/{billingId}")
    public ResponseEntity<InputStreamResource> generateInvoice(@PathVariable String billingId, Model model) {
        Billing billing = billingService.getBillingByid(billingId);
        model.addAttribute("companyName", "Your Company Name");
        model.addAttribute("billingId", billing.getBillingId());
        model.addAttribute("customerName", billing.getCustomerName());
        model.addAttribute("orderDate", LocalDate.now().toString()); // Current date
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

        model.addAttribute("products", productDTOs);

        // Calculate totals
        double subtotal = productDTOs.stream().mapToDouble(ProductDto::getSubtotal).sum();
        double taxPercentage = 18; // Example tax percentage
        double taxAmount = subtotal * (taxPercentage / 100);
        double totalAmount = subtotal + taxAmount;

        model.addAttribute("subtotal", subtotal);
        model.addAttribute("taxPercentage", taxPercentage);
        model.addAttribute("taxAmount", taxAmount);
        model.addAttribute("totalAmount", totalAmount);
        Context context = new Context();
        context.setVariables(model.asMap());
        String htmlContent = templateEngine.process("invoice", context);

        // Convert HTML to PDF
        ByteArrayOutputStream pdfOutputStream = new ByteArrayOutputStream();
        ITextRenderer renderer = new ITextRenderer();
        renderer.setDocumentFromString(htmlContent);
        renderer.layout();
        try {
            renderer.createPDF(pdfOutputStream);
        } catch (DocumentException e) {
            throw new RuntimeException(e);
        }
        renderer.finishPDF();

        // Prepare the PDF for download
        byte[] pdfBytes = pdfOutputStream.toByteArray();
        ByteArrayInputStream pdfInputStream = new ByteArrayInputStream(pdfBytes);

        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=invoice_" + billingId + ".pdf");

        return ResponseEntity.ok()
                .headers(headers)
                .contentType(MediaType.APPLICATION_PDF)
                .body(new InputStreamResource(pdfInputStream));
    }
}