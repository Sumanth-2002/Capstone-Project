import { CommonModule } from '@angular/common';
import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms'; // Import FormsModule for ngModel
import { HeaderComponent } from '../header/header.component';
import { SidebarComponent } from '../side-bar/side-bar.component';

@Component({
  selector: 'app-store-products',
  standalone: true, // Mark the component as standalone
  imports: [FormsModule,CommonModule, HeaderComponent,SidebarComponent], // Add FormsModule to imports
  templateUrl: './store-products.component.html',
  styleUrls: ['./store-products.component.css'],
})
export class StoreProductsComponent {
  // Dummy Data for Products
  products = [
    {
      productId: 'P001',
      name: 'Product A',
      description: 'Description A',
      category: 'Category 1',
      costPrice: 100,
      quantity: 50,
      status: 'Available',
    },
    {
      productId: 'P002',
      name: 'Product B',
      description: 'Description B',
      category: 'Category 2',
      costPrice: 200,
      quantity: 30,
      status: 'Available',
    },
    {
      productId: 'P003',
      name: 'Product C',
      description: 'Description C',
      category: 'Category 1',
      costPrice: 150,
      quantity: 20,
      status: 'Available',
    },
    {
      productId: 'P004',
      name: 'Product D',
      description: 'Description D',
      category: 'Category 3',
      costPrice: 300,
      quantity: 40,
      status: 'Available',
    },
    {
      productId: 'P005',
      name: 'Product E',
      description: 'Description E',
      category: 'Category 2',
      costPrice: 250,
      quantity: 60,
      status: 'Available',
    },
  ];

  // Request Data Object
  requestData = {
    storeId: 'S001', // Example store ID
    storeName: 'Store A', // Example store name
    productId: '',
    productName: '',
    quantity: null,
  };

  // Control Pop-up Form Visibility
  showRequestForm = false;

  // Open Request Form
  openRequestForm(product: any) {
    this.requestData = {
      ...this.requestData,
      productId: product.productId,
      productName: product.name,
    };
    this.showRequestForm = true;
  }

  // Close Request Form
  closeRequestForm() {
    this.showRequestForm = false;
    this.resetForm();
  }

  // Send Request
  sendRequest() {
    const product = this.products.find(
      (p) => p.productId === this.requestData.productId
    );
    if (product) {
      product.status = 'Requested'; // Update status to "Requested"
    }
    this.closeRequestForm(); // Close the form
  }

  // Reset Form
  resetForm() {
    this.requestData = {
      storeId: 'S001',
      storeName: 'Store A',
      productId: '',
      productName: '',
      quantity: null,
    };
  }
}