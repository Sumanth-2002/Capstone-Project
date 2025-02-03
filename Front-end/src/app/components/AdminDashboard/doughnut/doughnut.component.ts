import { CommonModule } from '@angular/common';
import { Chart, registerables } from 'chart.js';
import { Component } from '@angular/core';

Chart.register(...registerables);

@Component({
  selector: 'app-doughnut',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './doughnut.component.html',
  styleUrls: ['./doughnut.component.css'],
})
export class DoughnutComponent {

  ngOnInit(): void {
    this.renderDoughnutChart();
  }

  renderDoughnutChart(): void {
    const productCategories = [
      'Laptops', 'Mobiles', 'Tablets', 'Accessories', 
      'Desktops', 'Smartwatches', 'Headphones', 'Cameras', 'Printers'
    ];

    const productsSold = [150, 300, 120, 200, 80, 180, 250, 130, 90]; 

    const colors = [
      'red', 'blue', 'green', 'orange', 'purple', 
      'cyan', 'magenta', 'yellow', 'brown'
    ];

    new Chart('doughnutChart', {
      type: 'doughnut',
      data: {
        labels: productCategories,
        datasets: [{
          label: 'Products Sold',
          data: productsSold,
          backgroundColor: colors,
          borderColor: 'black',
          borderWidth: 1
        }]
      },
      options: {
        responsive: true,
        animation: {
          duration: 1000,
          easing: 'easeInOutQuad',
          animateRotate: true,
          animateScale: true,
        },
        plugins: {
          legend: {
            position: 'bottom',
            display:false,
          },
          title: {
            display: true,
            text: 'Products Sold Per Category',
          }
        },
      }
    });
  }
}
