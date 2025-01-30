import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class AuthService {
  private apiUrl = 'http://localhost:9093/api/login/authenticate'; // Your backend API endpoint

  constructor(private http: HttpClient) {}

  // Login function that will authenticate the user
  login(userId: string, password: string): Observable<any> {
    return this.http.post(this.apiUrl, { userId, password });
  }

  // Save the JWT token in localStorage
  saveToken(token: string): void {
    localStorage.setItem('authToken', token);

    // Decode the JWT token to get the payload (role, userId)
    const decodedToken = this.getDecodedToken();

    if (decodedToken) {
      // Extract role and userId from the decoded token and save them separately
      localStorage.setItem('role', decodedToken.role);
      localStorage.setItem('userName', decodedToken.sub);
      localStorage.setItem('userId',decodedToken.userId);
    }
  }

  // Retrieve the JWT token from localStorage
  getToken(): string | null {
    return localStorage.getItem('authToken');
  }

  // Decode the JWT token to get user info (e.g., role)
  getDecodedToken(): any {
    const token = this.getToken();
    if (token) {
      const payload = token.split('.')[1];
      return JSON.parse(atob(payload)); // Decode JWT token payload
    }
    return null;
  }
  clearToken():void{
    localStorage.removeItem("authToken");
  }
  // Check if the user is authenticated by checking if a token exists and is valid
  isAuthenticated(): boolean {
    const token = this.getDecodedToken();
    return token != null; // Token must be present to be authenticated
  }

  // Logout by removing the JWT token from localStorage
  logout(): void {
    localStorage.removeItem('authToken');
  }
}
