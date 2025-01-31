import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class ProductService {
  // private baseUrl = 'http://localhost:9091/api/products'; // Replace with your backend URL
private baseUrl = 'http://localhost:8222/api/products';
  constructor(private http: HttpClient) {}

  // Fetch all vendors
  getAllProducts(): Observable<any[]> {
    return this.http.get<any[]>(`${this.baseUrl}/get-products/COMP1A1006`);
  }

  // Add a new vendor
  addProduct(product: any): Observable<any> {
    return this.http.post(`${this.baseUrl}bulk`, product);
  }

  // Upload bulk products from CSV
  uploadBulkProducts(file: File, companyId: string): Observable<any> {
    const formData = new FormData();
    formData.append('products_data', file);
    formData.append('companyId', companyId);
    return this.http.post(`${this.baseUrl}save-data-csv`, formData, {
      responseType: 'text' // Specify response type as text
    });
  }
}
