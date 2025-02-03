import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
@Injectable({
  providedIn: 'root', // Makes this service available app-wide
})
export class RequestService {
  private apiUrl = 'http://localhost:9092/api/stores';
    // private apiUrl = 'http://localhost:8222/api/stores';// Update with actual API URL
  constructor(private http: HttpClient) {}
  // Fetch pending requests from the API
  getPendingRequests(): Observable<any[]> {
    return this.http.get<any[]>(`${this.apiUrl}/get-requests/COMP1A1006`);
  }
  // Update request status
  updateRequestStatus(updatedRequest: any): Observable<any> {
    return this.http.put(`${this.apiUrl}/update-request`, updatedRequest);
  }
}
