import { Component, HostListener } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { HeaderComponent } from '../header/header.component';
import { SidebarComponent } from '../side-bar/side-bar.component';
import { BillingService } from '../../../service/billing.service';
import { HttpClient } from '@angular/common/http';
import { BillingProductService } from '../../../service/billing-product.service';
import { Products } from '../../../service/billing-product.service';

interface Product {
  productName: string;
  productId: string;
  quantity: number | null;
  showSuggestions: boolean;
  filteredProducts: { productId: string; productName: string }[];
}

@Component({
  selector: 'app-store-billing',
  standalone: true,
  imports: [FormsModule, CommonModule, HeaderComponent, SidebarComponent],
  templateUrl: './store-billing.component.html',
  styleUrls: ['./store-billing.component.css'],
})
export class StoreBillingComponent {
  billingId: string | null = null;
  billingData = {
    customerName: '',
    contact: '',
    storeId: 'STOR1728AA',
    storeName: 'TechStore Chennai',
    salesRepId: 'SREP0J1K2L',
  };

  products: Product[] = [
    { productName: '', productId: '', quantity: null, showSuggestions: false, filteredProducts: [] },
  ];

  // To store the fetched products from DB
  productsList: Products[] = [];

  newCustomer = { customerName: '', contact: '' };
  isCustomerModalOpen = false;
  isSalesRepModalOpen = false;
  newSalesRep: any = { salesRepName: '', contact: '' };

  constructor(
    private billingService: BillingService,
    private http: HttpClient,
    private billingProductService: BillingProductService
  ) {}

  ngOnInit() {
    this.loadProducts(); // Fetch products from DB when component initializes
  }

  loadProducts() {
    this.billingProductService.getProducts().subscribe({
      next: (data) => {
        this.productsList = data; // Store the fetched products
      },
      error: (error) => {
        console.error('Error fetching products:', error);
      },
    });
  }

  // Handle product input and filter suggestions
  onProductInput(index: number) {
    const inputValue = this.products[index].productId.toLowerCase();
    this.products[index].filteredProducts = this.productsList.filter((product) =>
      product.productId.toLowerCase().includes(inputValue)
    );
    this.products[index].showSuggestions = this.products[index].filteredProducts.length > 0;
  }
 
  // Select Product from Suggestion List
  selectProduct(index: number, product: Products) {
    this.products[index].productId = product.productId;
    this.products[index].productName = product.productName;
    this.products[index].showSuggestions = false;
  }

  addProduct() {
    this.products.push({ productName: '', productId: '', quantity: null, showSuggestions: false, filteredProducts: [] });
  }

  openSalesRepModal() { this.isSalesRepModalOpen = true; }
  closeSalesRepModal() { this.isSalesRepModalOpen = false; this.newSalesRep = { salesRepName: '', contact: '' }; }
  saveSalesRep() { console.log('New Sales Rep:', this.newSalesRep); this.closeSalesRepModal(); }

  openCustomerModal() { this.isCustomerModalOpen = true; }
  closeCustomerModal() { this.isCustomerModalOpen = false; }
  saveCustomer() {
    this.billingData.customerName = this.newCustomer.customerName;
    this.billingData.contact = this.newCustomer.contact;
    this.closeCustomerModal();
  }

  onSubmit() {
    const billingPayload = {
      customerId: 'CUST123',
      companyId: 'COMP1A1006',
      customerName: this.billingData.customerName,
      contact: this.billingData.contact,
      storeId: this.billingData.storeId,
      storeName: this.billingData.storeName,
      salesRepId: this.billingData.salesRepId,
      productBilledList: this.products.map((product) => ({
        productId: product.productId,
        productName: product.productName,
        quantity: product.quantity || 0,
      })),
    };

    this.billingService.addBilling(billingPayload).subscribe({
      next: (response) => {
        console.log('Billing Data Saved:', response);
        this.billingId = response.billingId;
        alert('Billing data saved successfully!');
      },
      error: (error) => {
        console.error('Error saving billing data:', error);
        alert('Error saving billing data. Please try again.');
      },
    });
  }

  generateInvoice() {
    if (!this.billingId) {
      alert('Billing ID is not available. Please save the billing data first.');
      return;
    }

    console.log('Generating invoice for Billing ID:', this.billingId);

    this.http.get(`http://localhost:9095/api/billing/invoice/generate-invoice/${this.billingId}`, { responseType: 'arraybuffer' }).subscribe({
      next: (response: ArrayBuffer) => {
        const pdfBlob = new Blob([response], { type: 'application/pdf' });
        const link = document.createElement('a');
        link.href = URL.createObjectURL(pdfBlob);
        link.download = `invoice_${this.billingId}.pdf`;
        link.click();
        console.log('Invoice generated and downloaded successfully!');
        alert('Invoice generated successfully!');
      },
      error: (error) => {
        console.error('Error generating invoice:', error);
        alert('Error generating invoice. Please try again.');
      },
    });
  }
}
