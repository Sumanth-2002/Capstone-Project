import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { FormsModule } from '@angular/forms';
import { RouterModule } from '@angular/router';
import { CommonModule } from '@angular/common';
import { AuthService } from '../auth/auth.service';
import { NgToastModule  } from 'ng-angular-popup';
import { NgToastService, ToasterPosition } from 'ng-angular-popup';

@Component({
  selector: 'app-login',
  standalone: true,
  imports: [FormsModule, RouterModule, CommonModule,NgToastModule],
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css'],
})
export class LoginComponent {
  loginData = {
    role: 'COMPANY',
    userId: '',
    password: '',
  };
  ToasterPosition = ToasterPosition;
  constructor(
    private router: Router,
    private authService: AuthService,
 // Inject MatSnackBar
 private toast: NgToastService
  ) {}

  onSubmit() {
    console.log('Login Data:', this.loginData);

    this.authService.login(this.loginData.userId, this.loginData.password).subscribe(
      (response: any) => {
        console.log(response);
        const jwt = response.jwt;
        this.authService.saveToken(jwt);

        const decodedToken = this.authService.getDecodedToken();
        const serverRole = decodedToken?.role;

        if (this.validateRole(serverRole)) {
     
          setTimeout(() => {
            this.toast.danger("",'Login Successful', 3000);
            this.redirectUserBasedOnRole(serverRole);
          }, 2000); // Delay redirection to show toast
        } else {
          console.error('Role mismatch!');
          this.toast.danger("Roles mismatch",'Login Failed', 3000);
          setTimeout(() => {
            this.authService.clearToken();
          }, 2000); // Delay clearing token
        }
      },
      (error) => {
        console.error('Authentication failed:', error);
        this.toast.danger("Please check your credentials",'Login failed !! ', 3000);

      }
    );
  }



  private validateRole(serverRole: string): boolean {
    const selectedRole = this.loginData.role;
    const roleMapping: { [key: string]: string } = {
      company: 'COMPANY',
      store: 'STORE',
    };
    return roleMapping[selectedRole] === serverRole;
  }

  private redirectUserBasedOnRole(role: string): void {
    if (role === 'COMPANY') {
      this.router.navigate(['/admin-home']);
    } else if (role === 'STORE') {
      this.router.navigate(['/store-home']);
    } else {
      this.router.navigate(['/']);
    }
  }
}
