package com.luiscm.currencyexchange.interfaces;

import com.luiscm.currencyexchange.exception.DataProviderException;
import com.luiscm.currencyexchange.exception.InvalidCurrencyException;

public interface CurrencyConverterInterface {
    double convert(double amount, String fromCurrency, String toCurrency) 
    throws InvalidCurrencyException, DataProviderException;
    void showAvailableCurrencies(IOHandlerInterface ioHandler) throws DataProviderException;
}