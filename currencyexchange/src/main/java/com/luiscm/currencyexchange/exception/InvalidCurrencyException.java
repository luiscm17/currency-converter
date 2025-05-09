package com.luiscm.currencyexchange.exception;

public class InvalidCurrencyException extends IllegalArgumentException {
    public InvalidCurrencyException(String message) {
        super(message);
    }
}