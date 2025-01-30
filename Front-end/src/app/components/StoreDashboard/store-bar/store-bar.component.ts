import { Component, OnInit } from '@angular/core';
import { Chart, registerables } from 'chart.js';
Chart.register(...registerables);

@Component({
  selector: 'app-store-bar',
  standalone: true,
  imports: [],
  templateUrl: './store-bar.component.html',
  styleUrl: './store-bar.component.css'
})
export class StoreBarComponent implements OnInit {
  monthlyRevenue: number[] = [];
  months: string[] = ['Jan', 'Feb', 'Mar', 'Apr', 'May', 'Jun', 'Jul', 'Aug', 'Sep', 'Oct', 'Nov', 'Dec'];

  constructor() {}

  ngOnInit(): void {
    // Replace this with actual data from your service
    this.monthlyRevenue = [800000, 1200000, 950000, 1500000, 880000, 1100000, 1300000, 750000, 950000, 1400000, 850000, 1600000];
    this.renderStoreRevenueChart();
  }

  renderStoreRevenueChart() {
    const threshold = 1000000; // 10 lakhs threshold
    const myChart = new Chart('storeRevenueChart', {
      type: 'bar',
      data: {
        labels: this.months,
        datasets: [{
          label: 'Monthly Revenue',
          data: this.monthlyRevenue,
          backgroundColor: this.monthlyRevenue.map(value => 
            value >= threshold ? '#4CAF50' : '#FFA726'  // Green for >= 10L, Orange for < 10L
          ),
          borderWidth: 0
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
            text: 'Monthly Revenue',
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
            display: true,
            position: 'top',
            labels: {
              generateLabels: (chart) => [{
                text: '≥ ₹10 Lakhs',
                fillStyle: '#4CAF50',
                strokeStyle: '#4CAF50',
                lineWidth: 0,
              }, {
                text: '< ₹10 Lakhs',
                fillStyle: '#FFA726',
                strokeStyle: '#FFA726',
                lineWidth: 0,
              }]
            }
          },
          tooltip: {
            callbacks: {
              label: function(context) {
                let value = context.raw as number;
                return `Revenue: ₹${(value/100000).toFixed(2)} Lakhs`;
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
              text: 'Revenue (₹)'
            },
            ticks: {
              callback: function(value, index, values) {
                return `₹${((value as number)/100000).toFixed(1)}L`;
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
            }
          }
        }
      }
    });
  }
}