import { Component } from '@angular/core';
import { HeaderComponent } from '../header/header.component';
import { SidebarComponent } from '../side-bar/side-bar.component';

@Component({
  selector: 'app-store-leaderboard',
  imports: [HeaderComponent,SidebarComponent],
  templateUrl: './store-leaderboard.component.html',
  styleUrl: './store-leaderboard.component.css'
})
export class StoreLeaderboardComponent {

}
