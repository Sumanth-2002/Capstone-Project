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
    const productCategories = ['Laptops', 'Mobiles', 'Tablets', 'Accessories', 'Desktops'];
    const productsSold = [150, 300, 120, 200, 80]; // Hardcoded number of products sold per category
    const colors = ['red', 'blue', 'green', 'orange', 'purple'];

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
          duration: 1000, // Animation duration in ms (1 second)
          easing: 'easeInOutQuad', // Easing function (easeInOutQuad is a common smooth easing)
          animateRotate: true, // Whether to animate the rotation of the chart
          animateScale: true, // Whether to animate the scale of the chart (like increasing size)
        },
        plugins: {
          legend: {
            position: 'bottom',
            display:false,
          },
          title: {
            display: true,
            text: 'Products Sold',
            

          }
        },
      }
    });
  }
}
