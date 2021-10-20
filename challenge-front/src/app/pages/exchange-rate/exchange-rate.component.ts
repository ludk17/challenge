import { Component, OnInit } from '@angular/core';
import { ExchangeRateService } from 'src/app/services/exchange-rate.service';

@Component({
  selector: 'app-exchange-rate',
  templateUrl: './exchange-rate.component.html',
  styleUrls: ['./exchange-rate.component.scss']
})
export class ExchangeRateComponent implements OnInit {

  public exchangeRate: any = {
    fromCurrency: '',
    toCurrency: '',
    amount: 0
  }

  public exchangeRateResult: any = {};

  constructor(private service: ExchangeRateService) { }
  

  ngOnInit(): void {
  }

  public calc() {
    this.service.calc(this.exchangeRate)
    .subscribe({
      next: res => { 
        this.exchangeRateResult = res;
      },
      error: err => {
        this.exchangeRateResult = {"message": "Error al procesar request"}
      }
    })
  }

}
