package com.luismendoza.challenge.controllers;

import com.luismendoza.challenge.entities.ExchangeRate;
import com.luismendoza.challenge.repositories.IExchangeRateRepository;
import com.luismendoza.challenge.requests.AddExchangeRateRequest;
import com.luismendoza.challenge.requests.CalculateExchangeRateRequest;
import com.luismendoza.challenge.responses.CalculateExchangeRateResponse;
import com.luismendoza.challenge.services.ExchangeRateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import rx.Single;

import javax.validation.Valid;
import java.util.List;

@RestController
@CrossOrigin(origins = "*")
public class ExchangeRateController {

    @Autowired
    private ExchangeRateService service;

//    public ExchangeRateController(ExchangeRateService service) {
//        this.service = service;
//    }

    @GetMapping(value = "/")
    public Single<List<ExchangeRate>> index() {
        return service.all();
    }

    @PostMapping("/")
    public Single<ExchangeRate> store(@Valid @RequestBody AddExchangeRateRequest request) {
        return service.add(request);
    }

    @PostMapping("/calculate")
    public Single<CalculateExchangeRateResponse> calculate(@RequestBody CalculateExchangeRateRequest request) {
        return service.calculate(request);
    }

}
