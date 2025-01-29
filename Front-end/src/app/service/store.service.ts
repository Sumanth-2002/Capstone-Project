import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class StoreService {

  private baseUrl = 'http://localhost:9092/api/stores'; // Replace with your backend URL
   
     constructor(private http: HttpClient) {}
   
    //  Fetch all vendors
     getAllStores(companyId: string): Observable<any[]> {
      return this.http.get<any[]>(`${this.baseUrl}/getAllStorealone/${companyId}`);
    }

    // getAllStores(companyId: string): Observable<any[]> {
    //   return this.http.get<any[]>(`${this.baseUrl}?companyId=${companyId}`);
    // }
     // Add a new vendor
     addStore(store: any): Observable<any> {
       return this.http.post(`${this.baseUrl}/addStore`, store,{ responseType: 'text' });
     }
}
