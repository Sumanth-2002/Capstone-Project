import { Component } from '@angular/core';
import { HeaderComponent } from '../header/header.component';
import { SidebarComponent } from '../side-bar/side-bar.component';

@Component({
  selector: 'app-store-home',
  imports: [HeaderComponent, SidebarComponent],
  templateUrl: './store-home.component.html',
  styleUrl: './store-home.component.css'
})
export class StoreHomeComponent {

}
