package com.luiscm.currencyexchange.config;

import java.net.URI;

public class Config {
    public static String getExchangeRateUrl(String baseCurrency) {
        return BASE_URL + API_KEY + "/latest/" + baseCurrency;
    }
    public static final String API_KEY = System.getenv("EXCHANGE_RATE_API_KEY");
    public static final String BASE_URL = "https://v6.exchangerate-api.com/v6/";


    public static URI getApiURL() {
        if (API_KEY == null || API_KEY.isBlank()) {
            throw new RuntimeException("API key no configurada. Establezca EXCHANGE_RATE_API_KEY");
        }
        return URI.create(BASE_URL + API_KEY + "/codes");
    }
}