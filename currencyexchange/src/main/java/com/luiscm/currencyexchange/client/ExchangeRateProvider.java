package com.luiscm.currencyexchange.client;

import com.luiscm.currencyexchange.model.ExchangeRate;

public interface ExchangeRateProvider {
    ExchangeRate getExchangeRates(String base) throws Exception;
}