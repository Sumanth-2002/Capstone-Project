import { CommonModule } from '@angular/common';
import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { HeaderComponent } from '../header/header.component';
import { SidebarComponent } from '../side-bar/side-bar.component';
import { BillingService } from '../../../service/billing.service';
import { HttpClient } from '@angular/common/http';

@Component({
  selector: 'app-store-billing',
  standalone: true,
  imports: [FormsModule, CommonModule, HeaderComponent, SidebarComponent],
  templateUrl: './store-billing.component.html',
  styleUrls: ['./store-billing.component.css'],
})
export class StoreBillingComponent {
  billingId: string | null = null;

  // Billing Data Object
  billingData = {
    customerName: '',
    contact: '',
    storeId: '',
    storeName: '',
    salesRepId: '',
  };

  // Product Array
  products = [{ productName: '', productId: '', quantity: null }];

  // New Customer Object
  newCustomer = {
    customerName: '',
    contact: '',
  };

  // Modal State
  isCustomerModalOpen = false;

  // New properties for Sales Rep Modal
  isSalesRepModalOpen = false;
  newSalesRep: any = {
    salesRepName: '',
    contact: '',
  };

  // Open Sales Rep Modal
  openSalesRepModal() {
    this.isSalesRepModalOpen = true;
  }

  // Close Sales Rep Modal
  closeSalesRepModal() {
    this.isSalesRepModalOpen = false;
    this.newSalesRep = { salesRepName: '', contact: '' }; // Reset form
  }

  // Save Sales Rep
  saveSalesRep() {
    console.log('New Sales Rep:', this.newSalesRep);
    this.closeSalesRepModal();
  }

  // Open Customer Modal
  openCustomerModal() {
    this.isCustomerModalOpen = true;
  }

  // Close Customer Modal
  closeCustomerModal() {
    this.isCustomerModalOpen = false;
  }

  // Save Customer Details
  saveCustomer() {
    this.billingData.customerName = this.newCustomer.customerName;
    this.billingData.contact = this.newCustomer.contact;
    this.closeCustomerModal();
  }

  // Add Product Fields
  addProduct() {
    this.products.push({ productName: '', productId: '', quantity: null });
  }

  // Handle Form Submission
  constructor(private billingService: BillingService, private http: HttpClient) {}

  onSubmit() {
    // Prepare the payload for saving the billing data
    const billingPayload = {
      customerId: 'CUST123',
      companyId: 'COMP1A1006', // Assuming customerName is unique, replace with actual customerId if necessary
      customerName: this.billingData.customerName,
      contact: this.billingData.contact,
      storeId: this.billingData.storeId,
      storeName: this.billingData.storeName,
      salesRepId: this.billingData.salesRepId,
      productBilledList: this.products.map((product) => ({
        productId: product.productId,
        productName: product.productName,
        quantity: product.quantity || 0, // Default to 0 if quantity is missing
      })),
    };

    this.billingService.addBilling(billingPayload).subscribe({
      next: (response) => {
        console.log('Billing Data Saved:', response);  // Logs the response containing billingId, billDate, etc.

        // Store the billingId received from the response
        this.billingId = response.billingId;
        alert('Billing data saved successfully!');

        // Optionally, you can trigger the Generate Invoice function automatically
        // this.generateInvoice();
      },
      error: (error) => {
        console.error('Error saving billing data:', error);
        alert('Error saving billing data. Please try again.');
      },
    });
  }

  generateInvoice() {
    if (!this.billingId) {
      alert('Billing ID is not available. Please save the billing data first.');
      return;
    }
  
    console.log('Generating invoice for Billing ID:', this.billingId);
  
    // Make the GET request to generate the invoice using the billingId
    this.http.get(`http://localhost:9095/api/billing/invoice/generate-invoice/${this.billingId}`, { responseType: 'arraybuffer' }).subscribe({
      next: (response: ArrayBuffer) => {
        // Convert the response to a Blob (binary data)
        const pdfBlob = new Blob([response], { type: 'application/pdf' });
  
        // Create a link to trigger the file download
        const link = document.createElement('a');
        link.href = URL.createObjectURL(pdfBlob);
        link.download = `invoice_${this.billingId}.pdf`; // Set the download filename
        link.click(); // Trigger the download
  
        console.log('Invoice generated and downloaded successfully!');
        alert('Invoice generated successfully!');
      },
      error: (error) => {
        console.error('Error generating invoice:', error);
        alert('Error generating invoice. Please try again.');
      },
    });
  }
  

  }
