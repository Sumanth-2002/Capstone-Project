import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { FormsModule } from '@angular/forms'; // Import FormsModule for ngModel
import { RouterModule } from '@angular/router'; // Import RouterModule for navigation
import { CommonModule } from '@angular/common'; // Import CommonModule for common directives

@Component({
  selector: 'app-login',
  standalone: true, // Mark the component as standalone
  imports: [FormsModule, RouterModule, CommonModule], // Import required modules
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css'],
})
export class LoginComponent {
  // Object to hold login data
  loginData = {
    role: 'company', // Default role
    email: '',
    password: '',
  };

  constructor(private router: Router) {}

  // Function to handle form submission
  onSubmit() {
    console.log('Login Data:', this.loginData);
    // Add your login logic here (e.g., API call)
    alert('Login successful!');
  }

  // Function to navigate back to the landing page
  navigateToLandingPage() {
    this.router.navigate(['/']); // Navigate to the landing page
  }
}