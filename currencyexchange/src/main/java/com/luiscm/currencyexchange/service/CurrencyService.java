package com.luiscm.currencyexchange.service;

import java.util.List;

import com.luiscm.currencyexchange.client.ExchangeRateProvider;
import com.luiscm.currencyexchange.model.ExchangeRate;
import com.luiscm.currencyexchange.util.ConfigLoader;
import com.luiscm.currencyexchange.util.CurrencyValidator;

public class CurrencyService {
    private final ExchangeRateProvider rateProvider;
    private final CurrencyValidator validator;

    public CurrencyService(ExchangeRateProvider rateProvider, CurrencyValidator validator) {
        this.rateProvider = rateProvider;
        this.validator = validator;
    }

    // Método plantilla
    public final double convertCurrency(double amount, String baseCurrency, String targetCurrency) throws Exception {
        validator.validate(baseCurrency);
        validator.validate(targetCurrency);
        validateInput(amount);
        ExchangeRate rates = fetchExchangeRates(baseCurrency);
        validateTargetCurrency(rates, targetCurrency);
        return calculateConversion(amount, rates.getConversionRates().get(targetCurrency));
    }

    protected void validateInput(double amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("El monto debe ser mayor que cero");
        }
    }

    protected ExchangeRate fetchExchangeRates(String baseCurrency) throws Exception {
        return getExchangeRates(baseCurrency);
    }

    protected void validateTargetCurrency(ExchangeRate rates, String targetCurrency) {
        if (!rates.getConversionRates().containsKey(targetCurrency)) {
            throw new IllegalArgumentException("Divisa objetivo no soportada: " + targetCurrency);
        }
    }

    protected double calculateConversion(double amount, double rate) {
        return amount * rate;
    }

    protected ExchangeRate getExchangeRates(String baseCurrency) throws Exception {
        return rateProvider.getExchangeRates(baseCurrency);
    }

    public List<String> getSupportedCurrencies() {
        return ConfigLoader.getSupportedCurrencies();
    }
}