package com.luismendoza.challenge.services;

import com.luismendoza.challenge.entities.ExchangeRate;
import com.luismendoza.challenge.requests.AddExchangeRateRequest;
import com.luismendoza.challenge.requests.CalculateExchangeRateRequest;
import com.luismendoza.challenge.responses.CalculateExchangeRateResponse;
import rx.Single;

import java.util.List;

public interface ExchangeRateService {
    Single<List<ExchangeRate>> all();
    Single<ExchangeRate> add(AddExchangeRateRequest request);
    Single<CalculateExchangeRateResponse> calculate(CalculateExchangeRateRequest request);
}
