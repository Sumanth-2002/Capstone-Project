import { CommonModule } from '@angular/common';
import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms'; // Import FormsModule for ngModel
import { HeaderComponent } from '../header/header.component';
import { SidebarComponent } from '../side-bar/side-bar.component';

@Component({
  selector: 'app-store-billing',
  standalone: true, // Mark the component as standalone
  imports: [FormsModule, CommonModule,HeaderComponent,SidebarComponent], // Add FormsModule to imports
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
    productName: '',
    productId: '',
    quantity: null,
  };

  // Function to Handle Form Submission
  onSubmit() {
    console.log('Billing Data:', this.billingData);
    alert('Billing data saved successfully!');
  }

  // Function to Add Product
  addProduct() {
    console.log('Product Added:', this.billingData);
    alert('Product added to the bill!');
  }

  // Function to Generate Invoice
  generateInvoice() {
    console.log('Invoice Generated:', this.billingData);
    alert('Invoice generated successfully!');
  }
}