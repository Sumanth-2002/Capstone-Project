import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms'; // Import FormsModule for ngModel
import { HeaderComponent } from '../header/header.component';
import { SidebarComponent } from '../side-bar/side-bar.component';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-admin-product',
  standalone: true, 
  imports: [FormsModule, HeaderComponent,SidebarComponent, CommonModule], 
  templateUrl: './admin-products.component.html',
  styleUrls: ['./admin-products.component.css'],
})
export class AdminProductsComponent {
  // Dummy Data for Products
  products = [
    { id: 1, name: 'Product A', description: 'Description A', category: 'Category 1', costPrice: 100, quantity: 50 },
    { id: 2, name: 'Product B', description: 'Description B', category: 'Category 2', costPrice: 200, quantity: 30 },
    { id: 3, name: 'Product C', description: 'Description C', category: 'Category 1', costPrice: 150, quantity: 20 },
    { id: 4, name: 'Product D', description: 'Description D', category: 'Category 3', costPrice: 300, quantity: 40 },
    { id: 5, name: 'Product E', description: 'Description E', category: 'Category 2', costPrice: 250, quantity: 60 },
  ];

  // New Product Object
  newProduct = {
    id: 5,
    name: '',
    description: '',
    category: '',
    costPrice: 0,
    quantity: 0,
  };

  // Control Pop-up Form Visibility
  showAddProductForm = false;

  // Open Add Product Form
  openAddProductForm() {
    this.showAddProductForm = true;
  }

  // Close Add Product Form
  closeAddProductForm() {
    this.showAddProductForm = false;
    this.resetForm();
  }

  // Save Product
  saveProduct() {
    this.newProduct.id = this.products.length + 1; // Auto-generate ID
    this.products.push({ ...this.newProduct }); // Add new product to the list
    this.closeAddProductForm(); // Close the form
  }

  // Reset Form
  resetForm() {
    this.newProduct = {
      id: 5,
      name: '',
      description: '',
      category: '',
      costPrice: 0,
      quantity: 0,
    };
  }
}