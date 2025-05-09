package com.luiscm.currencyexchange.service;

import com.luiscm.currencyexchange.provider.RateProvider;
import com.luiscm.currencyexchange.provider.CurrencyListProvider;
import com.luiscm.currencyexchange.constants.ErrorMessages;
import com.luiscm.currencyexchange.exception.DataProviderException;
import com.luiscm.currencyexchange.exception.InvalidCurrencyException;
import com.luiscm.currencyexchange.model.ExchangeRate;

import java.util.Map;



public class CurrencyService {
    private final RateProvider rateProvider;
    private final CurrencyListProvider listProvider;

    public CurrencyService(RateProvider rateProvider, CurrencyListProvider listProvider) {
        this.rateProvider = rateProvider;
        this.listProvider = listProvider;
    }
    public ExchangeRate getExchangeRate(String baseCurrency) throws InvalidCurrencyException, DataProviderException {
        if (!isValidCurrency(baseCurrency)) {
            throw new InvalidCurrencyException(ErrorMessages.UNSUPPORTED_CURRENCY);
        }
        return rateProvider.getExchangeRates(baseCurrency);
    }

    public Map<String, String> getAvailableCurrencies() throws DataProviderException {
        return listProvider.getAvailableCurrencies();
    }

    private boolean isValidCurrency(String currencyCode) {
        try {
            return listProvider.getAvailableCurrencies().containsKey(currencyCode);
        } catch (DataProviderException e) {
            return false;
        }
    }

    public double convertCurrency(double amount, String fromCurrency, String toCurrency)
        throws InvalidCurrencyException, DataProviderException {
        fromCurrency = fromCurrency.toUpperCase();
        toCurrency = toCurrency.toUpperCase();
        CurrencyValidator.validateCurrencyCode(fromCurrency);
        CurrencyValidator.validateCurrencyCode(toCurrency);
        
        ExchangeRate exchangeRate = rateProvider.getExchangeRates(fromCurrency);
        CurrencyValidator.validateTargetCurrency(exchangeRate, toCurrency);
        
        return amount * exchangeRate.getRates().get(toCurrency);
    }
}

// Nueva interfaz para futuras extensiones