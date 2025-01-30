import { Component, OnInit } from '@angular/core';
import { Chart, registerables } from 'chart.js';
Chart.register(...registerables);

@Component({
  selector: 'app-store-line',
  standalone: true,
  imports: [],
  templateUrl: './store-line.component.html',
  styleUrl: './store-line.component.css'
})
export class StoreLineComponent implements OnInit {
  monthlyCustomers: number[] = [];
  months: string[] = ['Jan', 'Feb', 'Mar', 'Apr', 'May', 'Jun', 'Jul', 'Aug', 'Sep', 'Oct', 'Nov', 'Dec'];

  ngOnInit(): void {
    // Replace with actual data from your service
    this.monthlyCustomers = [67, 45, 75, 85, 50, 69, 50, 59, 70, 75, 85,92];
    this.renderCustomerChart();
  }

  renderCustomerChart() {
    const myChart = new Chart('customerLineChart', {
      type: 'line',
      data: {
        labels: this.months,
        datasets: [{
          label: 'Monthly Customers',
          data: this.monthlyCustomers,
          borderColor: '#2196F3',
          backgroundColor: 'rgba(33, 150, 243, 0.1)',
          fill: true,
          tension: 0.4,
          pointBackgroundColor: '#2196F3',
          pointBorderColor: '#fff',
          pointBorderWidth: 2,
          pointRadius: 4,
          pointHoverRadius: 6
        }]
      },
      options: {
        responsive: true,
        maintainAspectRatio: false,
        animation: {
          duration: 2000,
          easing: 'easeInOutQuart'
        },
        plugins: {
          title: {
            display: true,
            text: 'Customers Purchased in a Month',
            font: {
              size: 20,
              weight: 'bold'
            },
            padding: {
              top: 10,
              bottom: 30
            }
          },
          legend: {
            display: false
          },
          tooltip: {
            backgroundColor: 'rgba(33, 150, 243, 0.8)',
            titleFont: {
              size: 13
            },
            bodyFont: {
              size: 12
            },
            callbacks: {
              label: function(context) {
                return `Customers: ${context.raw}`;
              }
            }
          }
        },
        scales: {
          y: {
            beginAtZero: true,
            grid: {
              display: false
            },
            title: {
              display: true,
              text: 'No of Customers'
            },
            ticks: {
              font: {
                size: 12
              }
            }
          },
          x: {
            grid: {
              display: false
            },
            title: {
              display: true,
              text: 'Month'
            },
            ticks: {
              font: {
                size: 12
              }
            }
          }
        }
      }
    });
  }
}