package com.luiscm.currencyexchange.util;

import com.luiscm.currencyexchange.exception.InvalidCurrencyException;

public interface Validator<T> {
    void validate(T input) throws InvalidCurrencyException;
}