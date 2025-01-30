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
  labeldata: string[] = ['Store A', 'Store B', 'Store C', 'Store D', 'Store E'];
  realdata: number[] = [5000, 3000, 7000, 4500, 6000];
  colordata: string[] = ['#FF6384', '#36A2EB', '#FFCE56', '#4BC0C0', '#9966FF'];

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
        }]
      },
      options: {
        scales: {
          y: {
            grid: {
              display: false
            },
            beginAtZero: true,
            title: {
              display: true,
              text: 'Sales Amount ($)'
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
