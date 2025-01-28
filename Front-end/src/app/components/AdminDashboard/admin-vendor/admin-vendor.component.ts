import { Component } from '@angular/core';
import { HeaderComponent } from '../header/header.component';
import { SidebarComponent } from '../side-bar/side-bar.component';
import { CommonModule } from '@angular/common';
import { FormsModule, NgModel } from '@angular/forms';
import { VendorService } from '../../../service/vendor.service';

@Component({
  selector: 'app-admin-vendor',
  standalone: true,
  imports: [HeaderComponent,SidebarComponent, CommonModule,FormsModule],
  templateUrl: './admin-vendor.component.html',
  styleUrl: './admin-vendor.component.css'
})
export class AdminVendorComponent {

  // Dummy Data for Vendors
  vendors: any[] = [];

  // New Vendor Object
  newVendor = {
    // vendorID: '',
    companyID: '',
    gstin: '',
    vendorName: '',
    vendorAddress: '',
    contact: '',
    email: '',
    lastPurchased: new Date().toISOString().split('T')[0], // Default to today's date
    quantityPurchased: 0,
  };

  // Control Pop-up Form Visibility
  showAddVendorForm = false;

  constructor(private vendorService: VendorService) {} // Inject the service

  // Fetch vendors on component initialization
  ngOnInit(): void {
    this.getAllVendors();
  }

  // Fetch all vendors from the service
  getAllVendors(): void {
    this.vendorService.getAllVendors().subscribe({
      next: (vendors: any[]) => {
        this.vendors = vendors; // Assign fetched data to the vendors array
      },
      error: (err: any) => {
        console.error('Failed to fetch vendors:', err);
        alert('Failed to fetch vendors. Please try again later.');
      },
    });
  }

  // Open Add Vendor Form
  openAddVendorForm(): void {
    this.showAddVendorForm = true;
  }

  // Close Add Vendor Form
  closeAddVendorForm(): void {
    this.showAddVendorForm = false;
    this.resetForm();
  }

  // Save Vendor
  saveVendor(): void {
    this.vendorService.addVendor(this.newVendor).subscribe({
      next: (response: any) => {
        this.vendors.push(response); // Add the new vendor to the list
        this.closeAddVendorForm(); // Close the form
        alert('Vendor added successfully!');
      },
      error: (err: any) => {
        console.error('Failed to save vendor:', err);
        alert('Failed to save vendor. Please try again.');
      },
    });
  }

  // Reset Form
  resetForm(): void {
    this.newVendor = {
      companyID: '',
      gstin: '',
      vendorName: '',
      vendorAddress: '',
      contact: '',
      email: '',
      lastPurchased: new Date().toISOString().split('T')[0], // Default to today's date
      quantityPurchased: 0,
    };
  }
}