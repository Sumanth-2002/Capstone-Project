import { CommonModule } from '@angular/common';
import { Component } from '@angular/core';
import { Router, RouterModule } from '@angular/router';

@Component({
  selector: 'app-sidebar',
  imports: [RouterModule, CommonModule], // Add RouterModule to use RouterLink directive
  standalone: true, // Mark the component as standalone
  templateUrl: './side-bar.component.html',
  styleUrls: ['./side-bar.component.css'],
})
export class SidebarComponent {
  constructor(private router: Router) {}

  // Function to handle logout
  logout() {
    // Ask for confirmation before logging out
    const confirmLogout = window.confirm('Are you sure you want to log out?');
    
    if (confirmLogout) {
      // If user confirms, clear localStorage and navigate to the login page
      localStorage.removeItem('authToken');
      localStorage.removeItem('userId');
      localStorage.removeItem('userName');
      localStorage.removeItem('role');
      
      this.router.navigate(['/login']); // Redirect to login page after logout
      console.log('Logged out successfully');
    } else {
      // If user cancels, do nothing
      console.log('Logout cancelled');
    }
  }
  
}