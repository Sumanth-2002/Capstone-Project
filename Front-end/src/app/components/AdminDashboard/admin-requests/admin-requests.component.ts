import { CommonModule } from '@angular/common';
import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms'; // Import FormsModule for ngModel
import { HeaderComponent } from '../header/header.component';
import { SidebarComponent } from '../side-bar/side-bar.component';

@Component({
  selector: 'app-admin-requests',
  standalone: true, // Mark the component as standalone
  imports: [FormsModule, CommonModule, HeaderComponent, SidebarComponent], // Add FormsModule to imports
  templateUrl: './admin-requests.component.html',
  styleUrls: ['./admin-requests.component.css'],
})
export class AdminRequestsComponent {
  // Dummy Data for Requests
  requests = [
    {
      requestId: 'R001',
      storeId: 'S001',
      name: 'Store A',
      productId: 'P001',
      quantity: 50,
      status: 'Pending',
      vendorId: 'V001',
      productName: 'Product A',
    },
    {
      requestId: 'R002',
      storeId: 'S002',
      name: 'Store B',
      productId: 'P002',
      quantity: 30,
      status: 'Pending',
      vendorId: 'V002',
      productName: 'Product B',
    },
    {
      requestId: 'R003',
      storeId: 'S003',
      name: 'Store C',
      productId: 'P003',
      quantity: 20,
      status: 'Pending',
      vendorId: 'V003',
      productName: 'Product C',
    },
    {
      requestId: 'R004',
      storeId: 'S004',
      name: 'Store D',
      productId: 'P004',
      quantity: 40,
      status: 'Pending',
      vendorId: 'V004',
      productName: 'Product D',
    },
    {
      requestId: 'R005',
      storeId: 'S005',
      name: 'Store E',
      productId: 'P005',
      quantity: 60,
      status: 'Pending',
      vendorId: 'V005',
      productName: 'Product E',
    },
  ];

  // Restock Data Object
  restockData = {
    requestId: '',
    storeId: '',
    productId: '',
    vendorId: '',
    productName: '',
    quantity: null,
  };

  // Control Pop-up Form Visibility
  showRestockForm = false;

  // Open Restock Form
  openRestockForm(request: any) {
    this.restockData = { ...request }; // Populate form data
    this.showRestockForm = true;
  }

  // Close Restock Form
  closeRestockForm() {
    this.showRestockForm = false;
    this.resetForm();
  }

  // Save Restock
  saveRestock() {
    const request = this.requests.find(
      (r) => r.requestId === this.restockData.requestId
    );
    if (request) {
      request.status = 'Restocked'; // Update status to "Restocked"
    }
    this.closeRestockForm(); // Close the form
  }

  // Reset Form
  resetForm() {
    this.restockData = {
      requestId: '',
      storeId: '',
      productId: '',
      vendorId: '',
      productName: '',
      quantity: null,
    };
  }
}