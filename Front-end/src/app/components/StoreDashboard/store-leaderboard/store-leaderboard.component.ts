import { SidebarComponent } from '../side-bar/side-bar.component';
import { CommonModule } from '@angular/common';
import { HttpClient } from '@angular/common/http';
import { HeaderComponent } from '../header/header.component';
import { Component } from '@angular/core';
@Component({
  selector: 'app-store-leaderboard',
  imports: [HeaderComponent,SidebarComponent,CommonModule],
  
  templateUrl: './store-leaderboard.component.html',
  styleUrl: './store-leaderboard.component.css'
})
export class StoreLeaderboardComponent {
  leaderboardData: any[] = [];
  constructor(private http: HttpClient) {}
  ngOnInit(): void {
    this.fetchLeaderboardData();
  }
  fetchLeaderboardData(): void {
    // Replace with your backend API endpoint
    const apiUrl = 'http://localhost:9095/api/billing/get-leaderboard/STOR28116A';
    this.http.get<any[]>(apiUrl).subscribe(
      (data) => {
        this.leaderboardData = data;
      },
      (error) => {
        console.error('Error fetching leaderboard data:', error);
      }
    );
  }
}
