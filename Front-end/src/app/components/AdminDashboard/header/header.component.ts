import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-header',
  standalone:true,
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent implements OnInit {
  userId: string | null = ''; // To hold the userId from localStorage
  role: string | null = ''; // To hold the role from localStorage

  constructor() { }

  ngOnInit(): void {
    // Retrieve userId and role from localStorage
    this.userId = localStorage.getItem('userName');
    this.role = "ADMIN";
  }
}
