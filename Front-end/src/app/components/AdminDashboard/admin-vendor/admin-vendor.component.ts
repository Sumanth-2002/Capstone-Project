import { Component,OnInit } from '@angular/core';
import { HeaderComponent } from '../header/header.component';
import { SidebarComponent } from '../side-bar/side-bar.component';
import { CommonModule } from '@angular/common';
import { FormsModule, NgModel } from '@angular/forms';
import { VendorService } from '../../../service/vendor.service';
import { NgxPaginationModule, PaginationInstance } from 'ngx-pagination';


@Component({
  selector: 'app-admin-vendor',
  standalone: true,
  imports: [HeaderComponent,SidebarComponent, CommonModule,FormsModule,NgxPaginationModule],
  templateUrl: './admin-vendor.component.html',
  styleUrl: './admin-vendor.component.css'
})
export class AdminVendorComponent {

  // Dummy Data for Vendors
  vendors: any[] = [];
  filteredVendors: any[] = [];
  searchTerm: string ='';
  filterVendors: any[]=[];


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

   // Pagination
  public config: PaginationInstance = {
    id: 'custom',
    itemsPerPage: 5, // Number of items per page
    currentPage: 1,
  };

  // Pagination Limit Options
paginationLimits = [5, 10, 20, 50];

// Sorting
sortColumn: string = '';
sortDirection: 'asc' | 'desc' = 'asc';

  // Fetch vendors on component initialization
  ngOnInit(): void {
    this.getAllVendors();
    this.filteredVendors = this.vendors;

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

  onSearch(searchTerm: string) {
    this.filteredVendors = this.vendors.filter((vendor) =>
      vendor.name.toLowerCase().includes(searchTerm.toLowerCase())
    );
    this.config.currentPage = 1; // Reset to first page after search
  }

   // Pagination change page
  onPageChange(page: number) {
    this.config.currentPage = page;
  }

  // Change Pagination Limit
  onPaginationLimitChange(limit: number) {
    this.config.itemsPerPage = limit;
    this.config.currentPage = 1; // Reset to the first page when the limit changes
  }

  // Sorting functionality
  sort(column: string) {
    if (this.sortColumn === column) {
      this.sortDirection = this.sortDirection === 'asc' ? 'desc' : 'asc';
    } else {
      this.sortColumn = column;
      this.sortDirection = 'asc';
    }

    this.filteredVendors.sort((a, b) => {
      const valueA = a[column];
      const valueB = b[column];

      if (valueA < valueB) {
        return this.sortDirection === 'asc' ? -1 : 1;
      } else if (valueA > valueB) {
        return this.sortDirection === 'asc' ? 1 : -1;
      } else {
        return 0;
      }
    });
  }
}