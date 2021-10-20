import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { shareReplay } from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
export class ExchangeRateService {

  constructor(private http: HttpClient) { }

  public calc(body): Observable<any> {
    return this.http
      .post<any>('/calculate', body)
      .pipe(shareReplay());
  }

}
