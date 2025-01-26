import { Component } from '@angular/core';
import { HeaderComponent } from '../header/header.component';
import { SidebarComponent } from '../side-bar/side-bar.component';

@Component({
  selector: 'app-admin-store',
  imports: [HeaderComponent,SidebarComponent],
  templateUrl: './admin-store.component.html',
  styleUrl: './admin-store.component.css'
})
export class AdminStoreComponent {

}
