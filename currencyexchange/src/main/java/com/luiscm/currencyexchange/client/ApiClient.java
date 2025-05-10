package com.luiscm.currencyexchange.client;

import com.luiscm.currencyexchange.model.ExchangeRate;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import com.google.gson.Gson;

import com.luiscm.currencyexchange.util.ConfigLoader;

public class ApiClient implements ExchangeRateProvider {
    private static final HttpClient httpClient = HttpClient.newHttpClient();
    private static final String API_KEY = ConfigLoader.getProperty("api.key");
    private static final String API_URL = ConfigLoader.getProperty("api.url");

    public ExchangeRate getExchangeRates(String base) throws Exception {
        HttpRequest request = HttpRequest.newBuilder()
            .uri(URI.create(API_URL + API_KEY + "/latest/" + base))
            .GET()
            .build();

        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

        if (response.statusCode() != 200) {
            throw new RuntimeException("Error obteniendo tasas: " + response.body());
        }

        return new Gson().fromJson(response.body(), ExchangeRate.class);
    }
}
