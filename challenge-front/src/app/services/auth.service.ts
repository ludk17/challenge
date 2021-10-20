import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { tap, shareReplay } from 'rxjs/operators';
import { CookieService } from 'ngx-cookie-service';
import { Observable } from 'rxjs';
import { environment } from '../../environments/environment';

interface User {
  username: string;
  password: string;
}

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  private static AUTH_USER_TOKEN_KEY = 'challenge_token_key';

  private readonly httpOptions: { headers: HttpHeaders };

  constructor(private http: HttpClient, private cookieService: CookieService) {
    this.httpOptions = {
      headers: new HttpHeaders({'Content-Type': 'application/json'})
    };
  }

  public login(user): any {
    return this.http.post('/authenticate', user, this.httpOptions)
    .pipe(tap(res => this.saveCookie(res)))
    .pipe(shareReplay());
  }

  public getToken(): any {
    const cookie = this.cookieService.get(AuthService.AUTH_USER_TOKEN_KEY);
    return (!!cookie && cookie != 'undefined') ? JSON.parse(cookie) : null;
  }

  public logout(): void {
    this.deleteCookie();
  }

  public isAuthenticated(): boolean {
    return !!this.getToken();
  }

  public saveCookie(res): void {
    console.log("saving cookie")
    this.cookieService.set(AuthService.AUTH_USER_TOKEN_KEY, JSON.stringify(res));
  }

  private deleteCookie(): void {
    this.cookieService.delete(AuthService.AUTH_USER_TOKEN_KEY);
  }
}
