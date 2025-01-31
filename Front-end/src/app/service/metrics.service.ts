import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class MetricsService {
  // private apiUrl = 'http://localhost:9095/getTotalProductsSelled';
private apiUrl = 'http://localhost:8222/getTotalProductsSelled';
  constructor(private http:HttpClient) {}

  getProductSalesByStore(companyId: string): Observable<any> {
    return this.http.get(`${this.apiUrl}/${companyId}`);
  }
}
