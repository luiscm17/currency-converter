package com.luiscm.currencyexchange.client;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;

import com.luiscm.currencyexchange.config.Config;
import com.luiscm.currencyexchange.config.UrlBuilder;
import com.luiscm.currencyexchange.exception.ApiClientException;

// Versión simplificada del ApiClient
public class ApiClient {
    // Métodos principales
    public static String fetchSupportedCurrencies() throws IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder()
            .GET()
            .header("Authorization", "Bearer " + API_KEY)
            .uri(Config.getApiURL())
            .build();

        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

        if (response.statusCode() != 200) {
            throw new ApiClientException("Error API: " + response.statusCode());
        }
        return response.body();
    }
    // Eliminar método buildExchangeRateUrl
    private static final HttpClient httpClient = HttpClient.newHttpClient();
    private static final String API_KEY = Config.API_KEY;
    
    public static String fetchLatestRates(String baseCurrency) throws IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder()
            .GET()
            .header("Authorization", "Bearer " + API_KEY)
            .uri(URI.create(Config.getApiURL() + "?base=" + baseCurrency))
            .build();

        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

        if (response.statusCode() != 200) {
            throw new ApiClientException("Error API: " + response.statusCode());
        }
        return response.body();
    }
    public static String fetchExchangeRates(String baseCurrency) {
        HttpRequest request = HttpRequest.newBuilder()
            .uri(URI.create(UrlBuilder.buildExchangeRateUrl(baseCurrency)))
            .build();

        try {
            HttpResponse<String> response = httpClient.send(request, BodyHandlers.ofString());
            
            if(response.statusCode() != 200) {
                throw new ApiClientException("Error API - Código: " + response.statusCode() + " | Respuesta: " + response.body().substring(0, 100), null);
            }
            
            return response.body();
        } catch (IOException | InterruptedException e) {
            throw new ApiClientException("Error de comunicación con la API", e);
        }
    }
}