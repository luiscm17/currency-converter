package com.luiscm.currencyexchange.util;

import com.luiscm.currencyexchange.exception.InvalidCurrencyException;
import java.util.Currency;

public class CurrencyValidator implements Validator<String> {
    public void validate(String currencyCode) throws InvalidCurrencyException {
        try {
            Currency.getInstance(currencyCode);
        } catch (IllegalArgumentException e) {
            throw new InvalidCurrencyException("Código de moneda inválido: " + currencyCode);
        }
    }
}