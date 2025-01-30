import { CommonModule } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { FormsModule } from '@angular/forms'; // Import FormsModule for ngModel
import { HeaderComponent } from '../header/header.component';
import { SidebarComponent } from '../side-bar/side-bar.component';
import { NgxPaginationModule, PaginationInstance } from 'ngx-pagination';
import { HttpClient, HttpClientModule } from '@angular/common/http'; // Import HttpClient
import { catchError } from 'rxjs/operators';
import { of } from 'rxjs';

@Component({
  selector: 'app-store-products',
  standalone: true,
  imports: [
    FormsModule,
    CommonModule,
    HeaderComponent,
    SidebarComponent,
    NgxPaginationModule,
    HttpClientModule, // Add HttpClientModule to imports
  ],
  templateUrl: './store-products.component.html',
  styleUrls: ['./store-products.component.css'],
})
export class StoreProductsComponent implements OnInit {
  // Dummy Data for Products (will be replaced by fetched data)
  products: any[] = [];

  newProduct = {
    productName: '',
    productDescription: '',
    category: '',
    vendorId: '',
    vendorName: '',
    selling_Price: 0,
    cost_Price: 0,
    quantity: 0,
 
  };

  searchTerm: string = '';

  // Filtered Products for Display
  filteredProducts: any[] = [];

  // Request Data Object
  requestData = {
    storeId: 'STOR1728AA', // Example store ID
    storeName: 'TechStore Chennai', // Example store name
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

  // Inject HttpClient
  constructor(private http: HttpClient) {}

  ngOnInit() {
    // Fetch products from the backend on component initialization
    this.fetchProducts();
  }

  // Fetch products from the backend
  fetchProducts() {
    const apiUrl = 'http://localhost:9092/api/stores/get-store-products/STOR1728AA'; // Replace with your backend API URL

    this.http
      .get<any[]>(apiUrl)
      .pipe(
        catchError((error) => {
          console.error('Error fetching products:', error);
          return of([]); // Return an empty array in case of error
        })
      )
      .subscribe((data) => {
        console.log(data);
        this.products = data; // Update products with fetched data
        this.filteredProducts = this.products; // Initialize filtered products
      });
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
      product.productName.toLowerCase().includes(searchTerm.toLowerCase())
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

  // Control Pop-up Form Visibility
  showAddProductForm = false;

  // Open Add Product Form
  openAddProductForm() {
    this.showAddProductForm = true;
  }

  // Close Add Product Form
  closeAddProductForm() {
    this.showAddProductForm = false;
    this.resetForm();
  }

  // Reset Product Form
  resetProductForm() {
    this.newProduct = {
      productDescription:'',
      productName: '',
      category: '',
      vendorId: '',
      vendorName: '',
      selling_Price: 0,
      cost_Price: 0,
      quantity: 0,
    
    };
  }

  // Open Request Form
  openRequestForm(product: any) {
    this.requestData = {
      ...this.requestData,
      productId: product.productId,
      productName: product.productName,
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
      storeId: 'STOR1728AA',
      storeName: 'TechStore Chennai',
      productId: '',
      productName: '',
      quantity: null,
    };
  }
}