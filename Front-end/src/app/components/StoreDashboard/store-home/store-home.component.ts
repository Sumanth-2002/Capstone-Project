import { Component } from '@angular/core';
import { HeaderComponent } from '../header/header.component';
import { SidebarComponent } from '../side-bar/side-bar.component';
import { StoreBarComponent } from '../store-bar/store-bar.component';
import { StoreLineComponent } from '../store-line/store-line.component';
import { StoreDoughnutComponent } from '../store-doughnut/store-doughnut.component';

@Component({
  selector: 'app-store-home',
  imports: [HeaderComponent, SidebarComponent,StoreBarComponent,StoreLineComponent,StoreDoughnutComponent],
  templateUrl: './store-home.component.html',
  styleUrl: './store-home.component.css'
})
export class StoreHomeComponent {

}
