package com.luiscm.currencyexchange.service;

import com.luiscm.currencyexchange.provider.CurrencyListProvider;
import com.luiscm.currencyexchange.constants.ErrorMessages;
import java.util.Collections;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class CurrencyDataService {
    private final CurrencyListProvider listProvider;
    private volatile Map<String, String> currencies = new ConcurrentHashMap<>();

    public CurrencyDataService(CurrencyListProvider listProvider) {
        this.listProvider = listProvider;
        cargarDivisasIniciales();
    }

    private void cargarDivisasIniciales() {
        actualizarDivisas();
        if (currencies.isEmpty()) {
            throw new IllegalStateException(ErrorMessages.INITIAL_LOAD_FAILED);
        }
    }

    

    private void actualizarDivisas() {
        try {
            Map<String, String> newCurrencies = listProvider.getAvailableCurrencies();
            currencies.clear();
            currencies.putAll(newCurrencies);
        } catch (Exception e) {
            System.err.println(ErrorMessages.CURRENCY_UPDATE_ERROR + e.getMessage());
        }
    }

    public boolean isValidCurrency(String currency) {
        return currencies.containsKey(currency);
    }

    public Map<String, String> getCurrencies() {
        return Collections.unmodifiableMap(currencies);
    }
}
