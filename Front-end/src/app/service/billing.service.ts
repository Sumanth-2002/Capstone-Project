import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class BillingService {
  // private baseUrl = 'http://localhost:9095/api/billing';
  private baseUrl = 'http://localhost:8222/api/billing';
  constructor(private http: HttpClient) {}
  // Add Billing Data
  addBilling(billingData: any): Observable<any> {
    return this.http.post(`${this.baseUrl}`, billingData);
  }

  // Add Customer Data
  addCustomer(customerData: any): Observable<any> {
    return this.http.post(`${this.baseUrl}/customer`, customerData);
  }
  generateInvoice(billingId: string) {
    return this.http.get(`http://localhost:9095/api/billing/invoice/generate-invoice/${billingId}`);
  }
  
}
