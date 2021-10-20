package com.luismendoza.challenge.repositories;

import com.luismendoza.challenge.entities.ExchangeRate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface IExchangeRateRepository extends JpaRepository<ExchangeRate, Long> {
    @Query("SELECT er FROM ExchangeRate er WHERE er.fromCurrency = :fromCurrency AND er.toCurrency = :toCurrency")
    Optional<ExchangeRate> findByFromAndToCurrency(@Param("fromCurrency") String fromCurrency, @Param("toCurrency") String toCurrency);
}
