package com.luiscm.currencyexchange.provider;

import com.luiscm.currencyexchange.client.ApiClient;
import com.luiscm.currencyexchange.exception.DataProviderException;
import com.luiscm.currencyexchange.model.ExchangeRate;
import com.luiscm.currencyexchange.util.CurrencyJsonParser;

import java.util.Map;

public class FixerCurrencyProvider implements RateProvider, CurrencyListProvider {
    
    @Override
    public Map<String, String> getAvailableCurrencies() throws DataProviderException {
        try {
            String jsonResponse = ApiClient.fetchSupportedCurrencies();
            return CurrencyJsonParser.parseApiResponse(jsonResponse);
        } catch (Exception e) {
            throw new DataProviderException("Error fetching currencies: " + e.getMessage());
        }
    }

    @Override
    public ExchangeRate getExchangeRates(String baseCurrency) throws DataProviderException {
        try {
            String jsonResponse = ApiClient.fetchLatestRates(baseCurrency);
            return CurrencyJsonParser.parseExchangeRates(jsonResponse);
        } catch (Exception e) {
            throw new DataProviderException("Error fetching rates: " + e.getMessage());
        }
    }
}