import { CommonModule } from '@angular/common';
import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { HeaderComponent } from '../header/header.component';
import { SidebarComponent } from '../side-bar/side-bar.component';
import { BillingService } from '../../../service/billing.service';

@Component({
  selector: 'app-store-billing',
  standalone: true,
  imports: [FormsModule, CommonModule,HeaderComponent,SidebarComponent],
  templateUrl: './store-billing.component.html',
  styleUrls: ['./store-billing.component.css'],
})
export class StoreBillingComponent {
  // Billing Data Object
  billingData = {
    customerName: '',
    contact: '',
    storeId: '',
    storeName: '',
    salesRepId:''
  };

  // Product Array
  productBilledList = [{ productName: '', productId: '', quantity: null }];

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
    this.productBilledList.push({ productName: '', productId: '', quantity: null });
  }

  // Handle Form Submission
  constructor(private billingService: BillingService) {}

  onSubmit() {
    const billingPayload = {
      ...this.billingData,
      products: this.productBilledList,
    };
    console.log('Billing Data:', { ...this.billingData, products: this.productBilledList });
    alert('Billing data saved successfully!');
    this.billingService.addBilling(billingPayload).subscribe({
      next: (response) => {
        console.log('Billing data saved successfully:', response);
        alert('Billing data saved successfully!');
      },
      error: (error) => {
        console.error('Error saving billing data:', error);
        alert('Error saving billing data. Please try again.');
      },
    });
  }


  // Generate Invoice
  generateInvoice() {
    console.log('Invoice Generated:', { ...this.billingData, products: this.productBilledList });
    alert('Invoice generated successfully!');
  }

}