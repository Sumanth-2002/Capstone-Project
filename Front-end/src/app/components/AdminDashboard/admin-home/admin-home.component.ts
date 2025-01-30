import { Component } from '@angular/core';
import { HeaderComponent } from '../header/header.component';
import { SidebarComponent } from "../side-bar/side-bar.component";
import { DoughnutComponent } from '../doughnut/doughnut.component';
import { LineAdminComponent } from '../line-admin/line-admin.component';
import { AdminBarComponent } from '../admin-bar/admin-bar.component';

@Component({
  selector: 'app-admin-home',
  imports: [HeaderComponent, SidebarComponent,DoughnutComponent,LineAdminComponent,AdminBarComponent],
  templateUrl: './admin-home.component.html',
  styleUrl: './admin-home.component.css'
})
export class AdminHomeComponent {

}
