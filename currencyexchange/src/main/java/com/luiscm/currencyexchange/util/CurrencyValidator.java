package com.luiscm.currencyexchange.util;

import com.luiscm.currencyexchange.constants.ErrorMessages;
import com.luiscm.currencyexchange.exception.InvalidCurrencyException;
import com.luiscm.currencyexchange.model.ExchangeRate;
import com.luiscm.currencyexchange.provider.CurrencyListProvider;
import com.luiscm.currencyexchange.provider.FixerCurrencyProvider;
import com.luiscm.currencyexchange.exception.DataProviderException;

public class CurrencyValidator {
    private static final CurrencyListProvider listProvider = new FixerCurrencyProvider();
    
    public static void validateCurrencyCode(String currency) {
        try {
            if (!listProvider.getAvailableCurrencies().containsKey(currency)) {
                throw new InvalidCurrencyException(ErrorMessages.UNSUPPORTED_CURRENCY + currency);
            }
        } catch (DataProviderException e) {
            throw new InvalidCurrencyException(ErrorMessages.DATA_PROVIDER_ERROR);
        }
    }
    
    public static void validateTargetCurrency(ExchangeRate rates, String currency) {
        if (!rates.getRates().containsKey(currency)) {
            throw new InvalidCurrencyException(ErrorMessages.TARGET_CURRENCY_ERROR + currency);
        }
    }
}