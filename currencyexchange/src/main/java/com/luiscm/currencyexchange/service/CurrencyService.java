package com.luiscm.currencyexchange.service;

import com.luiscm.currencyexchange.client.ApiClient;
import com.luiscm.currencyexchange.model.ExchangeRate;
import java.io.IOException;

public class CurrencyService {
    
    public static ExchangeRate getExchangeRates(String baseCurrency) throws IOException, InterruptedException {
        return ApiClient.fetchExchangeRates(baseCurrency);
    }

    public static double convertCurrency(double amount, String baseCurrency, String targetCurrency) 
        throws IOException, InterruptedException, IllegalArgumentException {
        
        if (amount <= 0) {
            throw new IllegalArgumentException("El monto debe ser mayor a cero");
        }
        
        ExchangeRate rates = getExchangeRates(baseCurrency);
        Double rate = rates.getConversionRates().get(targetCurrency);
        
        if (rate == null) {
            throw new IllegalArgumentException("Moneda destino no válida: " + targetCurrency);
        }
        
        return amount * rate;
    }
}