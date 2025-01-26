import { CommonModule } from '@angular/common';
import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { HeaderComponent } from '../header/header.component';
import { SidebarComponent } from '../side-bar/side-bar.component';

@Component({
  selector: 'app-admin-store',
  standalone: true, // Mark the component as standalone
  imports: [FormsModule, CommonModule, HeaderComponent, SidebarComponent], 
  templateUrl: './admin-store.component.html',
  styleUrls: ['./admin-store.component.css'],
})
export class AdminStoreComponent {
  // Dummy Data for Stores
  stores = [
    { id: 'S001', name: 'Store A', address: '123 Main St', region: 'North' },
    { id: 'S002', name: 'Store B', address: '456 Elm St', region: 'South' },
    { id: 'S003', name: 'Store C', address: '789 Oak St', region: 'East' },
    { id: 'S004', name: 'Store D', address: '101 Pine St', region: 'West' },
    { id: 'S005', name: 'Store E', address: '202 Maple St', region: 'Central' },
  ];

  // New Store Object
  newStore = {
    id: '',
    name: '',
    address: '',
    region: '',
  };

  // Control Pop-up Form Visibility
  showAddStoreForm = false;

  // Open Add Store Form
  openAddStoreForm() {
    this.showAddStoreForm = true;
  }

  // Close Add Store Form
  closeAddStoreForm() {
    this.showAddStoreForm = false;
    this.resetForm();
  }

  // Save Store
  saveStore() {
    this.stores.push({ ...this.newStore }); // Add new store to the list
    this.closeAddStoreForm(); // Close the form
  }

  // Reset Form
  resetForm() {
    this.newStore = {
      id: '',
      name: '',
      address: '',
      region: '',
    };
  }
}