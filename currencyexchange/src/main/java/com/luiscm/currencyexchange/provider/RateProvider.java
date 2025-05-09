package com.luiscm.currencyexchange.provider;

import com.luiscm.currencyexchange.exception.DataProviderException;
import com.luiscm.currencyexchange.model.ExchangeRate;

// Nueva interfaz para futuras extensiones
public interface RateProvider {
    ExchangeRate getExchangeRates(String baseCurrency) throws DataProviderException;
}