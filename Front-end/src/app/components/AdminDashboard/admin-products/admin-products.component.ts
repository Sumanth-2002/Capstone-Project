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

  searchTerm: string ='';

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
    companyId: 'COMP1A1006',
  }

  showAddBulkProductForm: boolean = false;
  selectedFile: File | null = null;

  // Open Add Bulk Product Form
  openAddBulkProductForm() {
    this.showAddBulkProductForm = true;
  }

  // Close Add Bulk Product Form
  closeAddBulkProductForm() {
    this.showAddBulkProductForm = false;
    this.selectedFile = null; // Reset the selected file
  }

  // Handle File Selection
  onFileSelected(event: any) {
    this.selectedFile = event.target.files[0];
  }

  // Upload Bulk Products
  uploadBulkProducts() {
    if (!this.selectedFile) {
      alert('Please select a CSV file.');
      return;
    }

    this.productService
      .uploadBulkProducts(this.selectedFile, this.newProduct.companyId)
      .subscribe({
        next: (response: string) => {
          console.log('Bulk products uploaded successfully:', response);
          alert('Bulk products uploaded successfully!');
          this.closeAddBulkProductForm();
          this.fetchProducts(); // Refresh the product list
        },
        error: (error) => {
          console.error('Error uploading bulk products:', error);
          alert('Error uploading bulk products. Please try again.');
        },
      });
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

  // Add validation patterns
  private patterns = {
    productName: /^[A-Za-z\s]+$/, // Only alphabets and spaces
    vendorId: /^[A-Z0-9]+$/, // Uppercase letters and numbers only
    price: /^\d+(\.\d{1,2})?$/, // Numbers with up to 2 decimal places
    quantity: /^\d+$/ // Only positive integers
  };

  validationErrors = {
    productName: '',
    description: '',
    category: '',
    vendorId: '',
    vendorName: '',
    cost_Price: '',
    selling_Price: '',
    quantity: ''
  };

  // Modify saveProduct to include validation
  saveProduct() {
    if (!this.validateForm()) {
      return;
    }
    
    const productsList = [this.newProduct];
    
    this.productService.addProduct(productsList).subscribe({
      next: (savedProducts) => {
        this.products = this.products.concat(savedProducts);
        this.closeAddProductForm();
        this.resetValidationErrors();
      },
      error: (error) => {
        console.error('Error saving products:', error);
        alert('There was an error saving the products. Please try again.');
      }
    });
  }

  validateForm(): boolean {
    let isValid = true;
    this.resetValidationErrors();

    // Product Name validation
    if (!this.newProduct.productName || this.newProduct.productName.trim() === '') {
      this.validationErrors.productName = 'Product name is required';
      isValid = false;
    } else if (!this.patterns.productName.test(this.newProduct.productName)) {
      this.validationErrors.productName = 'Product name can only contain alphabets and spaces';
      isValid = false;
    }

    // Description validation
    if (!this.newProduct.description || this.newProduct.description.trim() === '') {
      this.validationErrors.description = 'Description is required';
      isValid = false;
    } else if (this.newProduct.description.length < 10) {
      this.validationErrors.description = 'Description must be at least 10 characters long';
      isValid = false;
    }

    // Category validation
    if (!this.newProduct.category || this.newProduct.category.trim() === '') {
      this.validationErrors.category = 'Category is required';
      isValid = false;
    } else if (!this.patterns.productName.test(this.newProduct.category)) {
      this.validationErrors.category = 'Category can only contain alphabets and spaces';
      isValid = false;
    }

    // Vendor ID validation
    if (!this.newProduct.vendorId || this.newProduct.vendorId.trim() === '') {
      this.validationErrors.vendorId = 'Vendor ID is required';
      isValid = false;
    } else if (!this.patterns.vendorId.test(this.newProduct.vendorId)) {
      this.validationErrors.vendorId = 'Vendor ID can only contain uppercase letters and numbers';
      isValid = false;
    }

    // Vendor Name validation
    if (!this.newProduct.vendorName || this.newProduct.vendorName.trim() === '') {
      this.validationErrors.vendorName = 'Vendor name is required';
      isValid = false;
    } else if (!this.patterns.productName.test(this.newProduct.vendorName)) {
      this.validationErrors.vendorName = 'Vendor name can only contain alphabets and spaces';
      isValid = false;
    }

    // Cost Price validation
    if (this.newProduct.cost_Price <= 0) {
      this.validationErrors.cost_Price = 'Cost price must be greater than 0';
      isValid = false;
    } else if (!this.patterns.price.test(this.newProduct.cost_Price.toString())) {
      this.validationErrors.cost_Price = 'Invalid price format. Use numbers with up to 2 decimal places';
      isValid = false;
    }

    // Selling Price validation
    if (this.newProduct.selling_Price <= 0) {
      this.validationErrors.selling_Price = 'Selling price must be greater than 0';
      isValid = false;
    } else if (!this.patterns.price.test(this.newProduct.selling_Price.toString())) {
      this.validationErrors.selling_Price = 'Invalid price format. Use numbers with up to 2 decimal places';
      isValid = false;
    } else if (this.newProduct.selling_Price <= this.newProduct.cost_Price) {
      this.validationErrors.selling_Price = 'Selling price must be greater than cost price';
      isValid = false;
    }

    // Quantity validation
    if (this.newProduct.quantity < 0) {
      this.validationErrors.quantity = 'Quantity cannot be negative';
      isValid = false;
    } else if (!this.patterns.quantity.test(this.newProduct.quantity.toString())) {
      this.validationErrors.quantity = 'Quantity must be a whole number';
      isValid = false;
    }

    return isValid;
  }

  resetValidationErrors() {
    this.validationErrors = {
      productName: '',
      description: '',
      category: '',
      vendorId: '',
      vendorName: '',
      cost_Price: '',
      selling_Price: '',
      quantity: ''
    };
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
      companyId: 'COMP1A1006',
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

  showUpdateStockForm: boolean = false;
  selectedProduct: any = {};
  addStock: number = 0;

  // Method to open the update stock form
  openUpdateStockForm(product: any) {
    this.selectedProduct = { ...product };
    this.showUpdateStockForm = true;
  }

  // Method to close the update stock form
  closeUpdateStockForm() {
    this.showUpdateStockForm = false;
    this.selectedProduct = {};
    this.addStock = 0;
  }

  // Method to save the updated stock
  saveStock() {
    const productIndex = this.filteredProducts.findIndex(p => p.productId === this.selectedProduct.productId);
    if (productIndex !== -1) {
      this.filteredProducts[productIndex].quantity += this.addStock;
    }
    this.closeUpdateStockForm();
  }
}
