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
  products: any[] = [];
  newProduct = {
    companyId: 'COMP1A1006',
    storeId: 'STOR1728AA',
    storeName: 'TechStore Chennai',
    productId: '',
    productName: '',
    productDescription: '',
    category: '',
    quantity: 0,
  };
  searchTerm: string = '';
  filteredProducts: any[] = [];
  requestData = {
    storeId: 'STOR1728AA',
    storeName: 'TechStore Chennai',
    productId: '',
    productName: '',
    productDescription: '',
    category: '',
    quantity: null,
  };
  showRequestForm = false;
  showAddProductForm = false;
  public config: PaginationInstance = {
    id: 'custom',
    itemsPerPage: 5,
    currentPage: 1,
  };
  paginationLimits = [5, 10, 20, 50];
  sortColumn: string = '';
  sortDirection: 'asc' | 'desc' = 'asc';

  constructor(private http: HttpClient) {}

  ngOnInit() {
    this.fetchProducts();
  }

  fetchProducts() {
    const apiUrl = 'http://localhost:9092/api/stores/get-store-products/STOR1728AA'; // Replace with your backend API URL

    this.http
      .get<any[]>(apiUrl)
      .pipe(
        catchError((error) => {
          console.error('Error fetching products:', error);
          return of([]);
        })
      )
      .subscribe((data) => {
        console.log(data);
        this.products = data;
        this.filteredProducts = this.products;
      });
  }
  saveProduct() {
    // Backend API call to save product
    const payload = this.newProduct;

    this.http
      .post('http://localhost:9092/api/stores/raise-request', payload) // Replace with your backend API URL
      .pipe(
        catchError((error) => {
          console.error('Error saving product:', error);
          return of(null); // Return null if error
        })
      )
      .subscribe((response) => {
        if (response) {
          alert('Request raised successfully');
          console.log('Product added successfully:', response);
          this.closeAddProductForm(); // Close the form after successful submission
          this.fetchProducts(); // Optionally refresh the product list
        } else {
          console.error('Failed to add product.');
        }
      });
  }
  onPageChange(page: number) {
    this.config.currentPage = page;
  }

  onPaginationLimitChange(limit: number) {
    this.config.itemsPerPage = limit;
    this.config.currentPage = 1;
  }

  onSearch(searchTerm: string) {
    this.filteredProducts = this.products.filter((product) =>
      product.productName.toLowerCase().includes(searchTerm.toLowerCase())
    );
    this.config.currentPage = 1;
  }

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

  openAddProductForm() {
    this.showAddProductForm = true;
  }

  closeAddProductForm() {
    this.showAddProductForm = false;
    this.resetProductForm();
  }

  resetProductForm() {
    
    this.newProduct = {
      companyId: 'COMP1A1006',
      storeId: 'STOR1728AA',
      storeName: 'TechStore Chennai',
      productId: '',
      productName: '',
      productDescription: '',
      category: '',
      quantity: 0,
    };
  }

  openRequestForm(product: any) {
    this.requestData = {
      storeId: 'STOR1728AA',
      storeName: 'TechStore Chennai',
      productId: product.productId,
      productName: product.productName,
      productDescription: product.productDescription,
      category: product.category,
      quantity: null,
    };
    this.showRequestForm = true;
  }

  closeRequestForm() {
    this.showRequestForm = false;
    this.resetForm();
  }

  resetForm() {
    this.requestData = {
      storeId: 'STOR1728AA',
      storeName: 'TechStore Chennai',
      productId: '',
      productName: '',
      productDescription: '',
      category: '',
      quantity: null,
    };
  }

  onSubmit() {
    this.sendRequest();
  }

  sendRequest() {
    const payload = {
      companyId: 'COMP1A1006', // Replace with actual company ID
      storeId: this.requestData.storeId,
      storeName: this.requestData.storeName,
      productId: this.requestData.productId,
      productName: this.requestData.productName,
      productDescription: this.requestData.productDescription,
      category: this.requestData.category,
      quantity: this.requestData.quantity,
      status: 'Requested', // Set the status as 'Requested'
    };

    this.sendRequestToBackend(payload).subscribe(
      (response) => {
        console.log('Request sent successfully:', response);
        alert('Request Sent successfully');
        this.closeRequestForm();
      },
      (error) => {
        console.error('Failed to send request:', error);
        alert('Failed to send request. Please try again.');
      }
    );
  }

  sendRequestToBackend(payload: any) {
    const apiUrl = 'http://localhost:9092/api/stores/raise-request'; // Replace with your API endpoint
    return this.http.post(apiUrl, payload);
  }
}
