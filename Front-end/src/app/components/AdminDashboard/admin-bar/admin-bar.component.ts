import { Component } from '@angular/core';
import { Chart, registerables } from 'chart.js';
Chart.register(...registerables);

@Component({
  selector: 'app-admin-bar',
  imports: [],
  templateUrl: './admin-bar.component.html',
  styleUrl: './admin-bar.component.css'
})
export class AdminBarComponent {
  labeldata: string[] = [
    'TechStore Chennai', 
    'Gadget World Bangalore', 
    'ElectroHub Hyderabad', 
    'Bright Electronics Kochi',
    'TechStore Visakhapatnam',
    'ElectroHub Tirupati',
    'Digital Hub Mumbai',
    
  ];

  realdata: number[] = [
    8500,   // Chennai
    7200,   // Bangalore
    6800,   // Hyderabad
    5500,   // Kochi
    4900,   // Visakhapatnam
    4200,   // Tirupati
    7800,   // Mumbai
  ];

  colordata: string[] = [
    '#4CAF50', '#2196F3', '#FFA726', '#FF7043', '#9C27B0', 
    '#FF5252', '#3F51B5', 
  ];

  ngOnInit(): void {
    this.Renderbarchart();
  }

  Renderbarchart() {
    const mychar = new Chart('barchart', {
      type: 'bar',
      data: {
        labels: this.labeldata,
        datasets: [{
          label: 'Store Sales',
          data: this.realdata,
          backgroundColor: this.colordata,
          barPercentage: 0.6,
          categoryPercentage: 1.0,
        }]
      },
      options: {
        plugins: {
          legend: {
            display: false
          },
          title: {
            display: true,
            text: 'Sales by each Store',
            font: {
              size: 20,
              weight: 'bold'
            },
            padding: {
              top: 10,
              bottom: 30
            }
          }
        },
        scales: {
          y: {
            grid: {
              display: false
            },
            beginAtZero: true,
            title: {
              display: true,
              text: 'Sales Amount (₹) in 1000\'s'
            }
          },
          x: {
            grid: {
              display: false
            },
            title: {
              display: true,
              text: 'Stores'
            }
          }
        }
      }
    });
  }
}