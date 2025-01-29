import { Component, OnInit } from '@angular/core';
import { FormsModule } from '@angular/forms'; // Import FormsModule for ngModel
import { HeaderComponent } from '../header/header.component';
import { SidebarComponent } from '../side-bar/side-bar.component';
import { CommonModule } from '@angular/common';
// import { Observable } from 'rxjs';
import { ProductService } from '../../../service/product.service';
import { NgxPaginationModule, PaginationInstance } from 'ngx-pagination';


@Component({
  selector: 'app-admin-product',
  standalone: true,
  imports: [FormsModule, HeaderComponent, SidebarComponent, CommonModule,NgxPaginationModule],
  templateUrl: './admin-products.component.html',
  styleUrls: ['./admin-products.component.css'],
})
export class AdminProductsComponent implements OnInit {
  // Product list fetched from backend
  products: any[] = [];
  filteredProducts: any[] = [];
  
  // Initialize as an empty array
  searchTerm: string ='';
  // filterProducts: any[]=[];

  // New Product Object
  newProduct ={
    // productId: '',
    productName: '',
    category: '',
    vendorId: '',
    vendorName: '',
    selling_Price: 0,
    cost_Price: 0,
    quantity: 0,
    description: '',
    // companyId: '',
  }


  // Control Pop-up Form Visibility
  showAddProductForm = false;

  constructor(private productService:  ProductService) {}

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

  // Fetch products from backend
  fetchProducts() {
    this.productService.getAllProducts().subscribe({
      next: (products: any[]) => {
        this.products = products;
        this.filteredProducts = products; // Update filteredProducts
        console.log(products);
      },
      error: (error: any) => {
        console.error('Error fetching products:', error);
        alert('Could not fetch products. Please try again later.');
      }
    });
  }
  

  // Open Add Product Form
  openAddProductForm() {
    this.showAddProductForm = true;
  }

  // Close Add Product Form
  closeAddProductForm() {
    this.showAddProductForm = false;
    this.resetForm();
  }

  // Save Product
  saveProduct() {
    // Prepare the list of products to be sent
    const productsList = [this.newProduct]; // Wrapping the single product in an array
    
    console.log('Saving products list:', productsList); // Log the products list data
  
    // Call the service to send the list of products
    this.productService.addProduct(productsList).subscribe(
      (savedProducts) => {
        // Assuming the API returns the list of saved products
        this.products = this.products.concat(savedProducts); // Append the saved products to the existing list
        this.closeAddProductForm(); // Close the form after saving
      },
      (error) => {
        console.error('Error saving products:', error);
        alert('There was an error saving the products. Please try again.');
      }
    );
  }
  
  

  // Reset Form
  resetForm() {
    this.newProduct ={
      // productId: '',
      productName: '',
      category: '',
      vendorId: '',
      vendorName: '',
      selling_Price: 0,
      cost_Price: 0,
      quantity: 0,
      description: '',
      // companyId: '',
    }
  
  }

  // Initialize and fetch products on component load

  onSearch(searchTerm: string) {
    //this.filteredProducts = this.products;

    this.filteredProducts = this.products.filter((product) =>
      product.productName.toLowerCase().includes(searchTerm.toLowerCase())
    );
    
    //this.config.currentPage = 1; // Reset to first page after search
  }
  
  ngOnInit() {
    this.fetchProducts();
    //this.filteredProducts = this.products;
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
}
