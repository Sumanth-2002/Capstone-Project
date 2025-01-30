import { CommonModule } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { AgChartsModule } from 'ag-charts-angular';
import { AgChartOptions, AgCharts } from 'ag-charts-community';

@Component({
  selector: 'app-line-admin',
  standalone: true,
  imports: [CommonModule, FormsModule, AgChartsModule],
  templateUrl: './line-admin.component.html',
  styleUrl: './line-admin.component.css'
})
export class LineAdminComponent{
 
}