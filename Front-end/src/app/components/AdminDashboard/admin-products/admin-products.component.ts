import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms'; // Import FormsModule for ngModel
import { HeaderComponent } from '../header/header.component';
import { SidebarComponent } from '../side-bar/side-bar.component';
import { CommonModule } from '@angular/common';
// import { Observable } from 'rxjs';
import { ProductService } from '../../../service/product.service';

@Component({
  selector: 'app-admin-product',
  standalone: true,
  imports: [FormsModule, HeaderComponent, SidebarComponent, CommonModule],
  templateUrl: './admin-products.component.html',
  styleUrls: ['./admin-products.component.css'],
})
export class AdminProductsComponent {
  // Product list fetched from backend
  products: any[] = [];
  
  // Initialize as an empty array
  searchTerm: string ='';
  filterProducts: any[]=[];

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
    description: 0,
    // companyId: '',
  }


  // Control Pop-up Form Visibility
  showAddProductForm = false;

  constructor(private productService:  ProductService) {}

  // Fetch products from backend
  fetchProducts() {
    this.productService.getAllProducts().subscribe(
      (products: any[]) => {
        this.products = products;
      },
      (error: any) => {
        console.error('Error fetching products:', error);
        alert('Could not fetch products. Please try again later.');
      }
    );
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
      description: 0,
      // companyId: '',
    }
  
  }

  // Initialize and fetch products on component load

  onSearch(searchValue: string){
    this.filterProducts = this.products.filter(
      product => product.productName.toLowerCase().
      includes(this.searchTerm.toLowerCase()));
  }
  ngOnInit() {
    this.fetchProducts();
  }
}
