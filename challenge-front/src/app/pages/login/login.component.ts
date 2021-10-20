import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { AuthService } from 'src/app/services/auth.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})
export class LoginComponent implements OnInit {

  public user: any = {
    username: 'challenge_api',
    password: 'password'
  };

  constructor(private auth: AuthService, private router: Router) { }

  ngOnInit(): void {
  }

  public login(): void {
    this.auth.login(this.user)
      .subscribe({
        next: res => this.router.navigate(['/exchange-rate']),
        error: err => {
          console.log(err);
        }
      });
  }

}
