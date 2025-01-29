import { CommonModule } from '@angular/common';
import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { HeaderComponent } from '../header/header.component';
import { SidebarComponent } from '../side-bar/side-bar.component';
import { StoreService } from '../../../service/store.service';

@Component({
  selector: 'app-admin-store',
  standalone: true, // Mark the component as standalone
  imports: [FormsModule, CommonModule, HeaderComponent, SidebarComponent], 
  templateUrl: './admin-store.component.html',
  styleUrls: ['./admin-store.component.css'],
})
export class AdminStoreComponent {
  // Dummy Data for Stores
  stores: any[] = [];
  companyId = 'COMP14652A'; // Hardcoded in the component


  constructor(public storeService: StoreService){}

  // New Store Object
  newStore = {
    // storeId: '',
    companyId: this.companyId,
    storeName: '',
    region: '',
    storeAddress: '',
    createdAt: null, // For a Date field, use null initially
  };
  

  // Control Pop-up Form Visibility
  showAddStoreForm = false;

  // Open Add Store Form
  openAddStoreForm() {
    this.resetForm(); 
    this.showAddStoreForm = true;

  }

  // Close Add Store Form
  closeAddStoreForm() {
    this.showAddStoreForm = false;
    this.resetForm();
  }

  ngOnInit() {
    this.fetchStores();
  }

  fetchStores(): void {
    this.storeService.getAllStores(this.companyId).subscribe({
      next: (stores: any[]) => {
        console.log('Fetched stores:', stores);
  
        // Update the frontend stores array with a fresh copy
        this.stores = stores.map(store => ({ ...store })); // Avoid accidental mutations
      },
      error: (error: any) => {
        console.error('Error fetching stores:', error);
        alert('Could not fetch stores. Please try again later.');
      },
    });
  }
  
  
  
  saveStore(): void {
    // Create a fresh copy of newStore to avoid reference issues
    const storeToAdd = { ...this.newStore };
  
    console.log('Saving store:', storeToAdd);
  
    this.storeService.addStore(storeToAdd).subscribe({
      next: (response: any) => {
        console.log('Response from backend:', response);
        
        // Add the response (newly added store) to the stores array
        this.stores.push({ ...response });
  
        // Close the form and reset
        this.closeAddStoreForm();
        alert('Store added successfully!');
      },
      error: (err: any) => {
        console.error('Error saving store:', err);
        alert('Failed to save the store. Please try again.');
      },
    });
  }
  
  
  

  
  // Reset FormS
  resetForm() {
    this.newStore = {
      // storeId: '',
      companyId: '',
      storeName: '',
      region: '',
      storeAddress: '',
      createdAt: null, // For a Date field, use null initially
    };
    
  }
}