import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { FormsModule } from '@angular/forms'; // Import FormsModule for ngModel
import { RouterModule } from '@angular/router'; // Import RouterModule for navigation
import { CommonModule } from '@angular/common'; // Import CommonModule for common directives
import { AuthService } from '../auth/auth.service'; // Import the AuthService to handle login

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
    userId: '',
    password: '',
  };

  constructor(private router: Router, private authService: AuthService) {}

  // Function to handle form submission
  onSubmit() {
    console.log('Login Data:', this.loginData);

    // Call the AuthService to authenticate the user
    this.authService.login(this.loginData.userId, this.loginData.password).subscribe(
      (response: any) => {
        const jwt = response.jwt;  // Assuming JWT token is returned as 'token'
        this.authService.saveToken(jwt);  // Save JWT token to localStorage

        // Redirect user based on role
        this.redirectUserBasedOnRole();
      },
      (error) => {
        console.error('Authentication failed:', error);
        alert('Login failed! Please check your credentials.');
      }
    );
  }

  // Redirect user based on role after successful login
  private redirectUserBasedOnRole(): void {
    const decodedToken = this.authService.getDecodedToken();
    const role = decodedToken?.role;  // Extract the role from the decoded token

    if (role === 'COMPANY') {
      this.router.navigate(['/admin-home']); // Redirect to company dashboard
    } else if (role === 'STORE') {
      this.router.navigate(['/store-home']); // Redirect to store dashboard
    } else {
      this.router.navigate(['/']); // Default redirect (could be login or homepage)
    }
  }
}
