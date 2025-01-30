import { CommonModule } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { Chart, registerables } from 'chart.js';

import { FormsModule } from '@angular/forms';
Chart.register(...registerables)
@Component({
  selector: 'app-line-admin',
  standalone: true,
  imports: [CommonModule, FormsModule,],
  templateUrl: './line-admin.component.html',
  styleUrl: './line-admin.component.css'
})
export class LineAdminComponent{
  
  ngOnInit(): void {
    this.renderLineChart();
  }
  
  renderLineChart() {
    const mychar = new Chart('linechart', {
      type: 'line',
      data: {
        labels: ['January', 'February', 'March', 'April', 'May', 'June', 'July', 'August', 'September', 'October', 'November', 'December'],
        datasets: [
          {
            label: '2024 Sales',
            data: [65, 87, 92, 81, 70, 55, 65, 59, 80, 81, 89, 102],
            borderColor: 'rgba(75, 192, 192, 1)',
            backgroundColor: 'rgba(75, 192, 192, 0.2)',
            borderWidth: 2,
            tension: 0.1
          },
          {
            label: '2023 Sales',
            data: [45, 70, 75, 85, 60, 50, 70, 49, 75, 80, 75, 89],
            borderColor: 'rgba(255, 99, 132, 1)',
            backgroundColor: 'rgba(255, 99, 132, 0.2)',
            borderWidth: 2,
            tension: 0.1
          },
          {
            label: '2022 Sales',
            data: [40, 55, 65, 75, 54, 62, 60, 55, 70, 75, 55, 50],
            borderColor: 'rgba(54, 162, 235, 1)',
            backgroundColor: 'rgba(54, 162, 235, 0.2)',
            borderWidth: 2,
            tension: 0.1
          }
        ]
      },
      options: {
        responsive: true,
        maintainAspectRatio: false,
        scales: {
          y: {
            beginAtZero: true,
            title: {
              display: true,
              text: 'Sales Amount'
            }
          },
          x: {
            title: {
              display: true,
              text: 'Month'
            }
          }
        },
        plugins: {
          legend: {
            position: 'bottom',
            labels: {
              padding: 20,
              usePointStyle: true,
              pointStyle: 'circle'
            }
          },
          title: {
            display: true,
            text: 'Yearly Sales Comparison',
            position: 'top',
            padding: 20,
            font: {
              size: 20
            }
          }
        },
        interaction: {
          intersect: false,
          mode: 'index'
        }
      }
    });

  }
}