package com.luiscm.currencyexchange.config;

public class UrlBuilder {
    public static String buildExchangeRateUrl(String baseCurrency) {
        return Config.BASE_URL + Config.API_KEY + "/latest/" + baseCurrency;
    }
}