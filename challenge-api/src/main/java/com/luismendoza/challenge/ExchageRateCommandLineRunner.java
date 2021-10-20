package com.luismendoza.challenge;

import com.luismendoza.challenge.entities.ExchangeRate;
import com.luismendoza.challenge.repositories.IExchangeRateRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

@Component
public class ExchageRateCommandLineRunner implements CommandLineRunner {

    private static final Logger log = LoggerFactory.getLogger(ExchageRateCommandLineRunner.class);


    private IExchangeRateRepository exchangeRateRepository;

    public ExchageRateCommandLineRunner(IExchangeRateRepository exchangeRateRepository) {
        this.exchangeRateRepository = exchangeRateRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        log.info("Insert example exchange rates");

        List<ExchangeRate> exchangeRates = Arrays.asList(
                new ExchangeRate("USD", "PEN", BigDecimal.valueOf(3.95)),
                new ExchangeRate("PEN", "USD", BigDecimal.valueOf(0.25)),
                new ExchangeRate("PEN", "EUR", BigDecimal.valueOf(0.22)),
                new ExchangeRate("EUR", "PEN", BigDecimal.valueOf(4.60)),
                new ExchangeRate("EUR", "USD", BigDecimal.valueOf(1.16)),
                new ExchangeRate("USD", "EUR", BigDecimal.valueOf(0.86))
        );

        exchangeRateRepository.saveAll(exchangeRates);
    }
}
