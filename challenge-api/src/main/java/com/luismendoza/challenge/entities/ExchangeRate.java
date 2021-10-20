package com.luismendoza.challenge.entities;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.math.BigDecimal;
import java.util.Date;

@Entity
public class ExchangeRate {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "from_currency")
    @NotBlank(message = "from currency is required")
    private String fromCurrency;

    @Column(name = "to_currency")
    @NotBlank(message = "to currency is required")
    private String toCurrency;

    @Column(precision = 12, scale = 4)
    private BigDecimal rate;

    public ExchangeRate() {
    }

    public ExchangeRate(String fromCurrency, String toCurrency, BigDecimal rate) {
        this.fromCurrency = fromCurrency;
        this.toCurrency = toCurrency;
        this.rate = rate;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFromCurrency() {
        return fromCurrency;
    }

    public void setFromCurrency(String fromCurrency) {
        this.fromCurrency = fromCurrency;
    }

    public String getToCurrency() {
        return toCurrency;
    }

    public void setToCurrency(String toCurrency) {
        this.toCurrency = toCurrency;
    }

    public BigDecimal getRate() {
        return rate;
    }

    public void setRate(BigDecimal rate) {
        this.rate = rate;
    }
}
