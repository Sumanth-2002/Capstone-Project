import { CommonModule } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { HeaderComponent } from '../header/header.component';
import { SidebarComponent } from '../side-bar/side-bar.component';
import { RequestService } from '../../../service/request.service'; // Import Service
@Component({
  selector: 'app-admin-requests',
  standalone: true,
  imports: [FormsModule, CommonModule, HeaderComponent, SidebarComponent],
  templateUrl: './admin-requests.component.html',
  styleUrls: ['./admin-requests.component.css'],
})
export class AdminRequestsComponent implements OnInit {
  requests: any[] = [];
  restockData = { requestId: '', storeId: '', productId: '', vendorId: '', productName: '', quantity: null };
  showRestockForm = false;
  constructor(private requestService: RequestService) {}
  ngOnInit(): void {
    this.fetchRequests();
  }
  // Fetch requests using service
  fetchRequests(): void {
    this.requestService.getPendingRequests().subscribe(
      (data) => {
        console.log('Fetched Requests:', data);
        // Filter out requests with status 'Restocked'
        this.requests = data
          .filter((item) => item.status !== 'Restocked') // Exclude 'Restocked' requests
          .map((item) => ({
            requestId: item.requestId,
            storeId: item.storeId,
            status: item.status,
            productId: item.productId,
            productName: item.productName,
            quantity: item.quantity,
          }));
      },
      (error) => console.error('Error fetching requests:', error)
    );
  }
  // Open Restock Form
  openRestockForm(request: any): void {
    this.restockData = { ...request };
    this.showRestockForm = true;
  }
  // Close Restock Form
  closeRestockForm(): void {
    this.showRestockForm = false;
    this.resetForm();
  }
  // Submit Restock Request
  saveRestock(): void {
    // Create a copy of the request with the updated status
    const updatedRequest = { ...this.restockData, status: 'Restocked' };
    this.requestService.updateRequestStatus(updatedRequest).subscribe(
      () => {
        console.log('✅ Status updated successfully');
        alert('✅ Restocked successfully');
        this.updateLocalRequestStatus(); // Update UI locally
        this.closeRestockForm(); // Close form after updating
      },
      (error) => {
        console.error('❌ Failed to update status:', error);
        alert('⚠️ Error updating status. Please try again.');
      }
    );
  }
  // Update UI Locally
  updateLocalRequestStatus(): void {
    this.requests = this.requests.filter((r) => r.requestId !== this.restockData.requestId);
  }
  // Reset Form
  resetForm(): void {
    this.restockData = { requestId: '', storeId: '', productId: '', vendorId: '', productName: '', quantity: null };
  }
}