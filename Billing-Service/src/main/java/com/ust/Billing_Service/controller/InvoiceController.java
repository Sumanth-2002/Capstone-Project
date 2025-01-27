package com.ust.Billing_Service.controller;
import com.ust.Billing_Service.service.InvoiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class InvoiceController {

    @Autowired
    private InvoiceService invoiceService;

    @GetMapping("/generate-invoice/{billingId}")
    public ResponseEntity<byte[]> generateInvoice(@PathVariable String billingId) {
        try {
            // Generate the HTML content
            String htmlContent = invoiceService.generateInvoiceHtml(billingId);

            // Generate the PDF from HTML
            byte[] pdfBytes = invoiceService.generatePdf(htmlContent);

            // Return the PDF as a downloadable file
            return ResponseEntity.ok()
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=invoice_" + billingId + ".pdf")
                    .contentType(MediaType.APPLICATION_PDF)
                    .body(pdfBytes);

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.internalServerError().build();
        }
    }
}

