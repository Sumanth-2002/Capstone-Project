import { CommonModule } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { FormsModule } from '@angular/forms'; // Import FormsModule for ngModel
import { HeaderComponent } from '../header/header.component';
import { SidebarComponent } from '../side-bar/side-bar.component';
import { NgxPaginationModule, PaginationInstance } from 'ngx-pagination';

@Component({
  selector: 'app-store-products',
  standalone: true, // Mark the component as standalone
  imports: [FormsModule, CommonModule, HeaderComponent, SidebarComponent,NgxPaginationModule], // Add FormsModule to imports
  templateUrl: './store-products.component.html',
  styleUrls: ['./store-products.component.css'],
})
export class StoreProductsComponent implements OnInit {
  // Dummy Data for Products
  products = [
    {
      productId: 'P001',
      name: 'Product A',
      description: 'Description A',
      category: 'Category 1',
      costPrice: 100,
      quantity: 50,
      status: 'Available',
    },
    {
      productId: 'P002',
      name: 'Product B',
      description: 'Description B',
      category: 'Category 1',
      costPrice: 120,
      quantity: 30,
      status: 'Available',
    },
    {
      productId: 'P003',
      name: 'Product C',
      description: 'Description C',
      category: 'Category 2',
      costPrice: 90,
      quantity: 70,
      status: 'Out of Stock',
    },
    {
      productId: 'P004',
      name: 'Product D',
      description: 'Description D',
      category: 'Category 3',
      costPrice: 150,
      quantity: 40,
      status: 'Available',
    },
    {
      productId: 'P005',
      name: 'Product E',
      description: 'Description E',
      category: 'Category 2',
      costPrice: 200,
      quantity: 20,
      status: 'Discontinued',
    },
    {
      productId: 'P006',
      name: 'Product F',
      description: 'Description F',
      category: 'Category 4',
      costPrice: 130,
      quantity: 15,
      status: 'Available',
    },
    {
      productId: 'P007',
      name: 'Product G',
      description: 'Description G',
      category: 'Category 3',
      costPrice: 110,
      quantity: 25,
      status: 'Out of Stock',
    },
    {
      productId: 'P008',
      name: 'Product H',
      description: 'Description H',
      category: 'Category 1',
      costPrice: 80,
      quantity: 60,
      status: 'Available',
    },
    {
      productId: 'P009',
      name: 'Product I',
      description: 'Description I',
      category: 'Category 5',
      costPrice: 140,
      quantity: 35,
      status: 'Available',
    },
    {
      productId: 'P010',
      name: 'Product J',
      description: 'Description J',
      category: 'Category 4',
      costPrice: 95,
      quantity: 45,
      status: 'Discontinued',
    },
    {
      productId: 'P011',
      name: 'Product K',
      description: 'Description K',
      category: 'Category 5',
      costPrice: 125,
      quantity: 55,
      status: 'Available',
    },
    {
      productId: 'P012',
      name: 'Product L',
      description: 'Description L',
      category: 'Category 2',
      costPrice: 105,
      quantity: 50,
      status: 'Out of Stock',
    },
    {
      productId: 'P013',
      name: 'Product M',
      description: 'Description M',
      category: 'Category 1',
      costPrice: 115,
      quantity: 10,
      status: 'Available',
    },
    {
      productId: 'P014',
      name: 'Product N',
      description: 'Description N',
      category: 'Category 3',
      costPrice: 135,
      quantity: 80,
      status: 'Available',
    },
    {
      productId: 'P015',
      name: 'Product O',
      description: 'Description O',
      category: 'Category 4',
      costPrice: 175,
      quantity: 90,
      status: 'Discontinued',
    },
    {
      productId: 'P016',
      name: 'Product P',
      description: 'Description P',
      category: 'Category 5',
      costPrice: 85,
      quantity: 100,
      status: 'Available',
    },
    {
      productId: 'P017',
      name: 'Product Q',
      description: 'Description Q',
      category: 'Category 2',
      costPrice: 145,
      quantity: 20,
      status: 'Out of Stock',
    },
    {
      productId: 'P018',
      name: 'Product R',
      description: 'Description R',
      category: 'Category 1',
      costPrice: 160,
      quantity: 65,
      status: 'Available',
    },
    {
      productId: 'P019',
      name: 'Product S',
      description: 'Description S',
      category: 'Category 3',
      costPrice: 155,
      quantity: 75,
      status: 'Available',
    },
    {
      productId: 'P020',
      name: 'Product T',
      description: 'Description T',
      category: 'Category 5',
      costPrice: 125,
      quantity: 85,
      status: 'Discontinued',
    }
  ];

  searchTerm: string = '';

  // Filtered Products for Display
  filteredProducts: any[] = [];

  // Request Data Object
  requestData = {
    storeId: 'S001', // Example store ID
    storeName: 'Store A', // Example store name
    productId: '',
    productName: '',
    quantity: null,
  };

  // Control Pop-up Form Visibility
  showRequestForm = false;

  // Pagination
  public config: PaginationInstance = {
    id: 'custom',
    itemsPerPage: 5, // Number of items per page
    currentPage: 1,
  };

  // Pagination Limit Options
  paginationLimits = [5, 10, 20, 50];

  // Sorting
  sortColumn: string = '';
  sortDirection: 'asc' | 'desc' = 'asc';

  ngOnInit() {
    // Initialize filtered products
    this.filteredProducts = this.products;
  }

  
  // Pagination change page
  onPageChange(page: number) {
    this.config.currentPage = page;
  }

  // Change Pagination Limit
  onPaginationLimitChange(limit: number) {
    this.config.itemsPerPage = limit;
    this.config.currentPage = 1; // Reset to the first page when the limit changes
  }

  // Search functionality
  onSearch(searchTerm: string) {
    this.filteredProducts = this.products.filter((product) =>
      product.name.toLowerCase().includes(searchTerm.toLowerCase())
    );
    this.config.currentPage = 1; // Reset to first page after search
  }

  // Sorting functionality
  sort(column: string) {
    if (this.sortColumn === column) {
      this.sortDirection = this.sortDirection === 'asc' ? 'desc' : 'asc';
    } else {
      this.sortColumn = column;
      this.sortDirection = 'asc';
    }

    this.filteredProducts.sort((a, b) => {
      const valueA = a[column];
      const valueB = b[column];

      if (valueA < valueB) {
        return this.sortDirection === 'asc' ? -1 : 1;
      } else if (valueA > valueB) {
        return this.sortDirection === 'asc' ? 1 : -1;
      } else {
        return 0;
      }
    });
  }

  // Open Request Form
  openRequestForm(product: any) {
    this.requestData = {
      ...this.requestData,
      productId: product.productId,
      productName: product.name,
    };
    this.showRequestForm = true;
  }

  // Close Request Form
  closeRequestForm() {
    this.showRequestForm = false;
    this.resetForm();
  }

  // Send Request
  sendRequest() {
    const product = this.products.find(
      (p) => p.productId === this.requestData.productId
    );
    if (product) {
      product.status = 'Requested'; // Update status to "Requested"
    }
    this.closeRequestForm(); // Close the form
  }

  // Reset Form
  resetForm() {
    this.requestData = {
      storeId: 'S001',
      storeName: 'Store A',
      productId: '',
      productName: '',
      quantity: null,
    };
  }
}