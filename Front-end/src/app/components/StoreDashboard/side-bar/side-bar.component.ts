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
    // Add your logout logic here (e.g., clear session, navigate to login page)
    this.router.navigate(['/']);
    console.log('Logged out successfully');
  }
}