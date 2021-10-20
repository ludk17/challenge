import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AnonymousGuard } from './guards/anonymous.guard';
import { AuthGuard } from './guards/auth.guard';
import { ExchangeRateComponent } from './pages/exchange-rate/exchange-rate.component';
import { LoginComponent } from './pages/login/login.component';

const routes: Routes = [
  { path: 'login', component: LoginComponent },
  { path: 'exchange-rate', component: ExchangeRateComponent, canActivate: [AuthGuard] },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
