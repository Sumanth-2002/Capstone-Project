import { Routes } from '@angular/router';
import { LandingPageComponent } from './components/LandingPage/landing-page/landing-page.component';
import { RegistrationComponent } from './components/LandingPage/registration/registration.component';
import { LoginComponent } from './components/LandingPage/login/login.component';

export const routes: Routes = [
    {path:'',component:LandingPageComponent},
    { path: 'register', component: RegistrationComponent },
    { path: 'login', component: LoginComponent },
];
