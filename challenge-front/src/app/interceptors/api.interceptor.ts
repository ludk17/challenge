import { Injectable } from '@angular/core';
import { HttpEvent, HttpInterceptor, HttpHandler, HttpRequest, HttpErrorResponse, HttpHeaders } from '@angular/common/http';
import { environment } from '../../environments/environment';
import { Observable } from 'rxjs/internal/Observable';
import { tap } from 'rxjs/operators';

import { AuthService } from '../services/auth.service';
import { Location } from '@angular/common';

@Injectable({
  providedIn: 'root'
})
export class ApiInterceptor implements HttpInterceptor {

  private LOGIN_URL = '/authenticate';

  constructor(private auth: AuthService, private location: Location) {
  }

  intercept(req: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {

    if (/^http(s)?.*/.test(req.url)) {
      return next.handle(req);
    }

    const token = this.auth.getToken();
    const request = {url: '', headers: new HttpHeaders()};

    request.url = `${environment.apiEndPoint}${req.url}`;
    req.headers.set('Content-Type', 'application/json');
    req.headers.set('Accept', 'application/json');

    if (token && req.url.indexOf(this.LOGIN_URL) === -1) {
      request.headers = new HttpHeaders({
        Authorization: `Bearer ${token.token}`
      });
    }

    const apiReq = req.clone(request);

    return next.handle(apiReq).pipe(tap((event: HttpEvent<any>) => {
      return;
    }, (res: any) => {
      if (res instanceof HttpErrorResponse && res.url.indexOf('/authenticate') === -1) {
        if (res.status === 401) {
          if (confirm('Your session finished!!!')) {
            this.auth.logout();
            window.location.href = this.location.prepareExternalUrl('login');
          }
        }
      }
    }));
  }
}
