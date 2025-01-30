import { Component, OnInit } from '@angular/core';
import { Chart, registerables } from 'chart.js';
Chart.register(...registerables);

@Component({
  selector: 'app-store-doughnut',
  standalone: true,
  imports: [],
  templateUrl: './store-doughnut.component.html',
  styleUrl: './store-doughnut.component.css'
})
export class StoreDoughnutComponent implements OnInit {
  products: string[] = [
    'Graphics Card', 'Router', 'Smart Bulb', 'VR Headset', 
    'Smartphone', 'External SSD', 'Air Purifier', 'Wireless Mouse',
    'Smart Door Lock', 'Noise Cancelling Headphones', 'Electric Kettle',
    'Laptop Holder', 'Laptop', 'Smart TV', 'Wireless Earbuds',
    'Gaming Console', 'Mechanical Keyboard', 'Smartwatch',
    'Bluetooth Speaker', 'CCTV Camera'
  ];

  // Stock quantities from your data
  stockQuantities: number[] = [
    110, 80, 120, 25, 40, 140, 30, 90,
    19, 40, 60, 100, 72, 5, 70,
    15, 60, 0, 42, 35
  ];

  // Calculate percentages based on total stock
  calculatePercentages(): number[] {
    const totalStock = this.stockQuantities.reduce((acc, curr) => acc + (curr > 0 ? curr : 0), 0);
    return this.stockQuantities.map(qty => 
      qty > 0 ? Number(((qty / totalStock) * 100).toFixed(1)) : 0
    );
  }

  colors: string[] = [
    '#FF6384', '#36A2EB', '#FFCE56', '#4BC0C0', '#9966FF',
    '#FF9F40', '#FF6384', '#C9CBCF', '#4BC0C0', '#FF6384',
    '#36A2EB', '#FFCE56', '#4BC0C0', '#9966FF', '#FF9F40',
    '#FF6384', '#C9CBCF', '#4BC0C0', '#FF6384', '#36A2EB'
  ];

  ngOnInit(): void {
    this.renderDoughnutChart();
  }

  // ... existing code ...

  renderDoughnutChart() {
    const salesData = this.calculatePercentages();

    const myChart = new Chart('productDoughnutChart', {
      type: 'doughnut',
      data: {
        labels: this.products,
        datasets: [{
          data: salesData,
          backgroundColor: this.colors,
          borderWidth: 0,
          hoverOffset: 10
        }]
      },
      options: {
        responsive: true,
        maintainAspectRatio: false,
        cutout: '60%',
        animation: {
          animateScale: true,
          animateRotate: true,
          duration: 2000,
          easing: 'easeInOutQuart'
        },
        plugins: {
          title: {
            display: true,
            text: 'Percentage of Products in Stock',
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
            display: false  // Changed this to false to remove legends
          },
          tooltip: {
            callbacks: {
              label: function(context) {
                return `${context.label}: ${context.raw}%`;
              }
            }
          }
        }
      }
    });
  }
}