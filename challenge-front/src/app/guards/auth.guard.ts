import { Injectable } from '@angular/core';
import { CanActivate, Router } from '@angular/router';
import { AuthService } from '../services/auth.service';

@Injectable({
  providedIn: 'root'
})
export class AuthGuard implements CanActivate {

  constructor(public auth: AuthService, public router: Router) {
  }

  canActivate(): boolean {
    console.log("hello world");
    if (!this.auth.isAuthenticated()) {
        console.log("not authenticate");
      this.router.navigate(['/login']).then(() => false);
    }
    return true;
  }
}
