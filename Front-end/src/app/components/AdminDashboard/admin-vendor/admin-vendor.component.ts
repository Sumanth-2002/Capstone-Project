import { Component } from '@angular/core';
import { HeaderComponent } from '../header/header.component';
import { SidebarComponent } from '../side-bar/side-bar.component';
import { CommonModule } from '@angular/common';
import { FormsModule, NgModel } from '@angular/forms';

@Component({
  selector: 'app-admin-vendor',
  standalone: true,
  imports: [HeaderComponent,SidebarComponent, CommonModule,FormsModule],
  templateUrl: './admin-vendor.component.html',
  styleUrl: './admin-vendor.component.css'
})
export class AdminVendorComponent {

  // Dummy Data for Vendors
  vendors = [
    { id: 1, name: 'Vendor A', gstNo: 'GST001', address: '123 Main St', contact: '9876543210', productsPurchased: 50 },
    { id: 2, name: 'Vendor B', gstNo: 'GST002', address: '456 Elm St', contact: '9876543211', productsPurchased: 30 },
    { id: 3, name: 'Vendor C', gstNo: 'GST003', address: '789 Oak St', contact: '9876543212', productsPurchased: 20 },
    { id: 4, name: 'Vendor D', gstNo: 'GST004', address: '101 Pine St', contact: '9876543213', productsPurchased: 40 },
    { id: 5, name: 'Vendor E', gstNo: 'GST005', address: '202 Maple St', contact: '9876543214', productsPurchased: 60 },
  ];

  // New Vendor Object
  newVendor = {
    id: 5,
    name: '',
    gstNo: '',
    contact: '',
    address: '',
    productsPurchased: 0,
  };

  // Control Pop-up Form Visibility
  showAddVendorForm = false;

  // Open Add Vendor Form
  openAddVendorForm() {
    this.showAddVendorForm = true;
  }

  // Close Add Vendor Form
  closeAddVendorForm() {
    this.showAddVendorForm = false;
    this.resetForm();
  }

  // Save Vendor
  saveVendor() {
    this.newVendor.id = this.vendors.length + 1; // Auto-generate ID
    this.vendors.push({ ...this.newVendor }); // Add new vendor to the list
    this.closeAddVendorForm(); // Close the form
  }

  // Reset Form
  resetForm() {
    this.newVendor = {
      id: 5,
      name: '',
      gstNo: '',
      contact: '',
      address: '',
      productsPurchased: 0,
    };
  }
}