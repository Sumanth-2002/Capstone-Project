import { CommonModule } from '@angular/common';
import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { HeaderComponent } from '../header/header.component';
import { SidebarComponent } from '../side-bar/side-bar.component';

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
  products = [{ productName: '', productId: '', quantity: null }];

  // New Customer Object
  newCustomer = {
    customerName: '',
    contact: '',
  };

  // Modal State
  isCustomerModalOpen = false;

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
  onSubmit() {
    console.log('Billing Data:', { ...this.billingData, products: this.products });
    alert('Billing data saved successfully!');
  }

  // Generate Invoice
  generateInvoice() {
    console.log('Invoice Generated:', { ...this.billingData, products: this.products });
    alert('Invoice generated successfully!');
  }
}