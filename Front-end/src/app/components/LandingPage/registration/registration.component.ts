import { CommonModule } from '@angular/common';
import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { Router, RouterModule } from '@angular/router';
import { validateConfirmPassword } from './validateconfirmpassword';
import { HttpClient, HttpClientModule } from '@angular/common/http';
@Component({
  selector: 'app-registration',
  standalone: true, 
  imports: [FormsModule, RouterModule, CommonModule],
  templateUrl: './registration.component.html',
  styleUrls: ['./registration.component.css'],
})
export class RegistrationComponent {
  // Object to hold registration data
  registrationData = {
    companyName: '',
    gstNumber: '',
    uinNumber: '',
    address: '',
    companyEmail: '',
    password: '',
    confirmPassword: '',
  };

  constructor(private router: Router, private http: HttpClient) {

  }

  onSubmit() {
    // Mapping the registration data from the form to the expected format for the backend
    const requestData = {
      GSTIN: this.registrationData.gstNumber,   // Mapping form field to backend property
      UIN: this.registrationData.uinNumber,     // Mapping form field to backend property
      name: this.registrationData.companyName,  // Mapping form field to backend property
      email: this.registrationData.companyEmail, // Mapping form field to backend property
      password: this.registrationData.password,  // Mapping form field to backend property
    };

    // Send POST request to the backend API
    this.http.post('http://localhost:9090/api/company/register', requestData)
      .subscribe(
        (        response: any) => {
          console.log('Registration successful:', response);
          alert('Registration successful!');
          // Optionally redirect after success
          this.router.navigate(['/login']);
        },
        (        error: any) => {
          console.error('Error during registration:', error);
          alert('Registration failed. Please try again.');
        }
      );
  }
}