package com.luiscm.currencyexchange.client;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.Properties;

import com.google.gson.Gson;
import com.luiscm.currencyexchange.model.ExchangeRate;

public class ApiClient {
    private static final HttpClient httpClient = HttpClient.newHttpClient();
    private static String API_KEY;
    private static String API_URL;

    static {
        try {
            Properties prop = new Properties();
            prop.load(ApiClient.class.getResourceAsStream("/config.properties"));
            API_KEY = prop.getProperty("api.key");
            API_URL = prop.getProperty("api.url");
        } catch (IOException e) {
            throw new RuntimeException("Error loading configuration", e);
        }
    }
    
    public static ExchangeRate fetchExchangeRates(String baseCurrency) throws IOException, InterruptedException {
        try {
            HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(API_URL + API_KEY + "/latest/" + baseCurrency))
                .build();

            HttpResponse<String> response = httpClient.send(request, BodyHandlers.ofString());
            
            if(response.statusCode() != 200) {
                throw new IOException("Error en la API: Código " + response.statusCode());
            }
            
            Gson gson = new Gson();
            return gson.fromJson(response.body(), ExchangeRate.class);
        } catch (Exception e) {
            throw new IOException("Error al obtener tasas de cambio: " + e.getMessage());
        }
    }
}