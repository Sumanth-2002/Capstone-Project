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
    role: 'COMPANY', // Default role
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
        console.log(response);
        const jwt = response.jwt; // Assuming JWT token is returned as 'token'
        this.authService.saveToken(jwt); // Save JWT token to localStorage

        // Decode the token to get the role
        const decodedToken = this.authService.getDecodedToken();
        const serverRole = decodedToken?.role; // Extract the role from the decoded token

        // Validate if the selected role matches the server role
        if (this.validateRole(serverRole)) {
          this.redirectUserBasedOnRole(serverRole); // Redirect based on the server role
        } else {
          console.error('Role mismatch!');
          alert('Role mismatch! Please select the correct role.');
          this.authService.clearToken(); // Clear the invalid token
        }
      },
      (error) => {
        console.error('Authentication failed:', error);
        alert('Login failed! Please check your credentials.');
      }
    );
  }
  private validateRole(serverRole: string): boolean {
    const selectedRole = this.loginData.role; // Get the selected role from the radio button

    // Map the selected role to the expected server role
    const roleMapping: { [key: string]: string } = {
      company: 'COMPANY', // If 'company' is selected, the server role should be 'COMPANY'
      store: 'STORE', // If 'store' is selected, the server role should be 'STORE'
    };

    // Check if the selected role matches the server role
    return roleMapping[selectedRole] === serverRole;
  }

  // Redirect user based on role after successful login
  private redirectUserBasedOnRole(role: string): void {
    if (role === 'COMPANY') {
      this.router.navigate(['/admin-home']); // Redirect to admin dashboard
    } else if (role === 'STORE') {
      this.router.navigate(['/store-home']); // Redirect to store dashboard
    } else {
      this.router.navigate(['/']); // Default redirect (could be login or homepage)
    }
  }
}
