import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class VendorService {
  private baseUrl = 'http://localhost:9094/api/vendors'; // Replace with your backend URL

  constructor(private http: HttpClient) {}

  // Fetch all vendors
  getAllVendors(): Observable<any[]> {
    return this.http.get<any[]>(`${this.baseUrl}/`);
  }

  // Add a new vendor
  addVendor(vendor: any): Observable<any> {
    return this.http.post(`${this.baseUrl}/`, vendor);
  }
}