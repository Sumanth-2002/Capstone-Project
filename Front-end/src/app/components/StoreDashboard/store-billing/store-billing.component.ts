import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { HeaderComponent } from '../header/header.component';
import { SidebarComponent } from '../side-bar/side-bar.component';
import { BillingService } from '../../../service/billing.service';
import { HttpClient } from '@angular/common/http';
import { BillingProductService } from '../../../service/billing-product.service';
import { Products } from '../../../service/billing-product.service';
import { StoreService } from '../../../service/store.service';
interface Product {
  productName: string;
  productId: string;
  quantity: number | null;
  showSuggestions: boolean;
  filteredProducts: { productId: string; productName: string }[];
}

interface SalesRep {
  salesRepId: string;
  storeId: string;
  name: string;
  contact: string;
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
    salesRepId: '',
    customerId:'',
  };

  products: Product[] = [
    { productName: '', productId: '', quantity: null, showSuggestions: false, filteredProducts: [] },
  ];
  
  salesRepsList: SalesRep[] = [];
  productsList: Products[] = [];
  newCustomer = { customerName: '', contact: '' };
  isCustomerModalOpen = false;
  isSalesRepModalOpen = false;
  newSalesRep: any = { salesRepName: '', contact: '' };

  filteredSalesReps: SalesRep[] = [];
  showSalesRepSuggestions = false;

  constructor(
    private billingService: BillingService,
    private http: HttpClient,
    private billingProductService: BillingProductService
  ) {}

  ngOnInit() {
    this.fetchSalesReps();
    this.loadProducts();
  }

  fetchSalesReps() {
    this.http.get<SalesRep[]>(`http://localhost:9092/api/stores/get-all-salesRep/STOR1728AA`).subscribe(
      (data) => {
        console.log(data);
        this.salesRepsList = data;
      },
      (error) => {
        console.error('Error fetching sales reps:', error);
        alert('Error fetching sales representatives. Please try again.');
      }
    );
  }
  onSalesRepFocus() {
    if (!this.billingData.salesRepId) {
      this.filteredSalesReps = this.salesRepsList; // Show all sales reps if no input is provided
      this.showSalesRepSuggestions = this.filteredSalesReps.length > 0;
    }
  }
  

  onSalesRepInput() {
    const inputValue = this.billingData.salesRepId.toLowerCase();
    if (inputValue) {
      this.filteredSalesReps = this.salesRepsList.filter((rep) =>
        rep.salesRepId.toLowerCase().includes(inputValue) || rep.name.toLowerCase().includes(inputValue)
      );
      this.showSalesRepSuggestions = this.filteredSalesReps.length > 0;
    } else {
      this.filteredSalesReps = [];
      this.showSalesRepSuggestions = false;
    }
  }

  selectSalesRep(rep: SalesRep) {
    this.billingData.salesRepId = rep.salesRepId;
    this.filteredSalesReps = [];
    this.showSalesRepSuggestions = false;
  }
  

  loadProducts() {
    this.billingProductService.getProducts().subscribe({
      next: (data) => {
        this.productsList = data;
      },
      error: (error) => {
        console.error('Error fetching products:', error);
        alert('Error fetching products. Please try again.');
      },
    });
  }

  onProductInput(index: number) {
    const inputValue = this.products[index].productId.toLowerCase();
  
    // Exclude the already selected products from the suggestions
    const selectedProductIds = this.products
      .filter((product, i) => i !== index && product.productId) // Exclude current product
      .map(product => product.productId.toLowerCase());
  
    this.products[index].filteredProducts = this.productsList.filter((product) =>
      product.productId.toLowerCase().includes(inputValue) ||
      product.productName.toLowerCase().includes(inputValue)
    ).filter(product => !selectedProductIds.includes(product.productId.toLowerCase()));
  
    this.products[index].showSuggestions = this.products[index].filteredProducts.length > 0;
  }
  selectProduct(index: number, product: Products) {
    this.products[index].productId = product.productId;
    this.products[index].productName = product.productName;
    this.products[index].showSuggestions = false;
  }

  addProduct() {
    this.products.push({
      productName: '',
      productId: '',
      quantity: null,
      showSuggestions: false,
      filteredProducts: [],
    });
  }

  openSalesRepModal() {
    this.isSalesRepModalOpen = true;
  }

  closeSalesRepModal() {
    this.isSalesRepModalOpen = false;
    this.newSalesRep = { salesRepName: '', contact: '' };
  }

  saveSalesRep() {
    console.log('New Sales Rep:', this.newSalesRep);

    this.closeSalesRepModal();
  }

  openCustomerModal() {
    this.isCustomerModalOpen = true;
  }

  closeCustomerModal() {
    this.isCustomerModalOpen = false;
  }

  saveCustomer() {
    const customerData = {
      customerName: this.newCustomer.customerName,
      contact: this.newCustomer.contact
    };
  
    this.billingService.addCustomer(customerData).subscribe(
      (response) => {
        console.log('API Response:', response);
  
        if (response && response.customerId) {
          console.log('Customer added successfully:', response);
          this.billingData.customerName = response.customerName;
          this.billingData.contact = response.contact;
          this.billingData.customerId=response.customerId;
          this.closeCustomerModal();
        } else {
          console.error('Unexpected API response format:', response);
        }
      },
      (error) => {
        console.error('Error while saving customer:', error);
      }
    );
  }
  


  onSubmit() {
    const billingPayload = {
      customerId: this.billingData.customerId,
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
        alert('Invoice generated successfully!');
        setTimeout(() => {
          this.billingData = {
            customerName: '',
            contact: '',
            storeId: 'STOR1728AA',
            storeName: 'TechStore Chennai',
            salesRepId: '',
            customerId: '',
          };
        }, 500);
        
      },
      error: (error) => {
        console.error('Error generating invoice:', error);
        alert('Error generating invoice. Please try again.');
      },
    });
  }
}
