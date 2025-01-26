import { Routes } from '@angular/router';
import { LandingPageComponent } from './components/LandingPage/landing-page/landing-page.component';
import { RegistrationComponent } from './components/LandingPage/registration/registration.component';
import { LoginComponent } from './components/LandingPage/login/login.component';
import { AdminHomeComponent } from './components/AdminDashboard/admin-home/admin-home.component';
import { AdminStoreComponent } from './components/AdminDashboard/admin-store/admin-store.component';
import { AdminVendorComponent } from './components/AdminDashboard/admin-vendor/admin-vendor.component';
import { AdminRequestsComponent } from './components/AdminDashboard/admin-requests/admin-requests.component';
import { AdminProductsComponent } from './components/AdminDashboard/admin-products/admin-products.component';
import { StoreHomeComponent } from './components/StoreDashboard/store-home/store-home.component';
import { StoreProductsComponent } from './components/StoreDashboard/store-products/store-products.component';
import { StoreBillingComponent } from './components/StoreDashboard/store-billing/store-billing.component';
import { StoreLeaderboardComponent } from './components/StoreDashboard/store-leaderboard/store-leaderboard.component';

export const routes: Routes = [
    {path:'', component:LandingPageComponent},
    { path: 'register', component: RegistrationComponent },
    { path: 'login', component: LoginComponent },

    { path: 'admin-home', component: AdminHomeComponent },
    { path: 'admin-vendor', component: AdminVendorComponent },
    { path: 'admin-product', component: AdminProductsComponent },
    { path: 'admin-store', component: AdminStoreComponent },
    { path: 'admin-request', component: AdminRequestsComponent },

    { path: 'store-home', component: StoreHomeComponent },
    { path: 'store-products', component: StoreProductsComponent },
    { path: 'store-billing', component: StoreBillingComponent },
    { path: 'store-leaderboard', component: StoreLeaderboardComponent },
];
