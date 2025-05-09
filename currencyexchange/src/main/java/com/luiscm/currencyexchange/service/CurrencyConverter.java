package com.luiscm.currencyexchange.service;

import java.util.Map;

import com.luiscm.currencyexchange.exception.DataProviderException;
import com.luiscm.currencyexchange.exception.InvalidCurrencyException;
import com.luiscm.currencyexchange.interfaces.CurrencyConverterInterface;
import com.luiscm.currencyexchange.interfaces.IOHandlerInterface;

public class CurrencyConverter implements CurrencyConverterInterface {
    private final CurrencyService currencyService;

    public CurrencyConverter(CurrencyService currencyService) {
        if (currencyService == null) throw new IllegalArgumentException("CurrencyService no puede ser nulo");
        this.currencyService = currencyService;
    }

    @Override
    public double convert(double amount, String fromCurrency, String toCurrency) throws InvalidCurrencyException, DataProviderException {
        return currencyService.convertCurrency(amount, fromCurrency, toCurrency);
    }

    @Override
    public void showAvailableCurrencies(IOHandlerInterface ioHandler) throws DataProviderException {
        Map<String, String> currencies = currencyService.getAvailableCurrencies();
        ioHandler.displayAvailableCurrencies(currencies);
    }
}
