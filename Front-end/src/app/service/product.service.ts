import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class ProductService {

 private baseUrl = 'http://localhost:9091/api/products/'; // Replace with your backend URL
 
   constructor(private http: HttpClient) {}
 
   // Fetch all vendors
   getAllProducts(): Observable<any[]> {
     return this.http.get<any[]>(`${this.baseUrl}`);
   }
 
   // Add a new vendor
   addProduct(product: any): Observable<any> {
     return this.http.post(`${this.baseUrl}bulk`, product);
   }

   
}
