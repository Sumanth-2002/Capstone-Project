import { CommonModule } from '@angular/common';
import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { Router, RouterModule } from '@angular/router';

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

  constructor(private router: Router) {}

  // Function to handle form submission
  onSubmit() {
    console.log('Registration Data:', this.registrationData);
    // Add your registration logic here (e.g., API call)
    alert('Registration successful!');
  }

  // Function to navigate back to the landing page
  navigateToLandingPage() {
    this.router.navigate(['/']); // Navigate to the landing page
  }
}