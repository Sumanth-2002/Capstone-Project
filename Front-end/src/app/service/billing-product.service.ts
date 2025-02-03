import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

// Ensure interface is named 'Products'
export interface Products {
  productId: string;
  productName: string;
  productDescription?: string;  // Made optional
  category?: string;            // Made optional
  quantity?: number; 
}

@Injectable({
  providedIn: 'root'
})
export class BillingProductService {
  private apiUrl = 'http://localhost:9092/api/stores/get-store-products/STOR1728AA';

  constructor(private http: HttpClient) {}

  // Ensure return type is 'Products[]'
  getProducts(): Observable<Products[]> {
    return this.http.get<Products[]>(this.apiUrl);
  }
}
