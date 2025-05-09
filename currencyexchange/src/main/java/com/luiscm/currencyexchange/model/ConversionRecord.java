package com.luiscm.currencyexchange.model;

import java.time.LocalDateTime;

public class ConversionRecord {
    private final double amount;
    private final String fromCurrency;
    private final String toCurrency;
    private final double result;
    private final LocalDateTime timestamp;

    public ConversionRecord(double amount, String fromCurrency, String toCurrency, double result) {
        this.amount = amount;
        this.fromCurrency = fromCurrency;
        this.toCurrency = toCurrency;
        this.result = result;
        this.timestamp = LocalDateTime.now();
    }

    // Getters
    public double getAmount() { return amount; }
    public String getFromCurrency() { return fromCurrency; }
    public String getToCurrency() { return toCurrency; }
    public double getResult() { return result; }
    public LocalDateTime getTimestamp() { return timestamp; }
}