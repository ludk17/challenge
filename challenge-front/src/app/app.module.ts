import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { HTTP_INTERCEPTORS, HttpClientModule } from '@angular/common/http';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { LoginComponent } from './pages/login/login.component';

import { ApiInterceptor } from './interceptors/api.interceptor';
import { CookieService } from 'ngx-cookie-service';
import { FormsModule } from '@angular/forms';
import { ExchangeRateComponent } from './pages/exchange-rate/exchange-rate.component';


@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    ExchangeRateComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    FormsModule
  ],
  providers: [{
    provide: HTTP_INTERCEPTORS,
    useClass: ApiInterceptor,
    multi: true
  }, CookieService],
  bootstrap: [AppComponent]
})
export class AppModule { }
