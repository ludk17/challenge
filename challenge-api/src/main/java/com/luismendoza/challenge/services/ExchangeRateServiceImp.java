package com.luismendoza.challenge.services;

import com.luismendoza.challenge.entities.ExchangeRate;
import com.luismendoza.challenge.repositories.IExchangeRateRepository;
import com.luismendoza.challenge.requests.AddExchangeRateRequest;
import com.luismendoza.challenge.requests.CalculateExchangeRateRequest;
import com.luismendoza.challenge.responses.CalculateExchangeRateResponse;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import rx.Single;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

@Service
public class ExchangeRateServiceImp implements ExchangeRateService {

    private IExchangeRateRepository repository;

    public ExchangeRateServiceImp(IExchangeRateRepository repository) {
        this.repository = repository;
    }

    public Single<List<ExchangeRate>> all() {
        return Single.create(subscriber -> subscriber.onSuccess(repository.findAll()));
    }

    public Single<ExchangeRate> add(AddExchangeRateRequest request) {
        return Single.create(subscriber -> {
            Optional<ExchangeRate> optional = repository.findByFromAndToCurrency(request.getFromCurrency(), request.getToCurrency());
            ExchangeRate exchangeRate;
            if (!optional.isPresent()) {
                exchangeRate = repository.save(mapExchangeRate(request));
            } else {
                exchangeRate = repository.save(mapExchangeRate(optional.get(), request));
            }
            subscriber.onSuccess(exchangeRate);
        });
    }

    @Override
    public Single<CalculateExchangeRateResponse> calculate(CalculateExchangeRateRequest request) {
        return Single.create(subscriber -> {
            Optional<ExchangeRate> optional = repository.findByFromAndToCurrency(request.getFromCurrency(), request.getToCurrency());
            if (!optional.isPresent())
                subscriber.onError(new EntityNotFoundException("No se encuentra el tipo de cambio solicitado"));
            else
                subscriber.onSuccess(mapCalculateExchangeResponse(optional.get(), request));
        });
    }

    private CalculateExchangeRateResponse mapCalculateExchangeResponse(ExchangeRate exchangeRate, CalculateExchangeRateRequest request) {
        return CalculateExchangeRateResponse.builder()
                .toCurrency(exchangeRate.getToCurrency())
                .fromCurrency(exchangeRate.getFromCurrency())
                .requestedAmount(request.getAmount())
                .rate(exchangeRate.getRate())
                .totalAmount(request.getAmount().multiply(exchangeRate.getRate()))
                .build();
    }

    private ExchangeRate mapExchangeRate(AddExchangeRateRequest request) {
        ExchangeRate exchangeRate = new ExchangeRate();
        BeanUtils.copyProperties(request, exchangeRate);
        return exchangeRate;
    }

    private ExchangeRate mapExchangeRate(ExchangeRate exchangeRate, AddExchangeRateRequest request) {
        exchangeRate.setRate(request.getRate());
        return exchangeRate;
    }
}
