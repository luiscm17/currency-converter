package com.luiscm.currencyexchange.util;

import com.google.gson.JsonObject;
import com.google.gson.JsonArray;
import com.google.gson.JsonParser;
import com.luiscm.currencyexchange.constants.ErrorMessages;
import com.luiscm.currencyexchange.model.ExchangeRate;

import java.util.HashMap;
import java.util.Map;

public class CurrencyJsonParser {
    public static Map<String, String> parseApiResponse(String jsonResponse) {
        System.out.println("DEBUG - JSON Response: " + jsonResponse); // Temporal para ver estructura real
        try {
            JsonObject jsonObject = JsonParser.parseString(jsonResponse).getAsJsonObject();
            JsonArray supportedCodes = jsonObject.getAsJsonArray("supported_codes");
            
            Map<String, String> currencies = new HashMap<>();
            for (int i = 0; i < supportedCodes.size(); i++) {
                JsonArray pair = supportedCodes.get(i).getAsJsonArray();
                currencies.put(pair.get(0).getAsString(), pair.get(1).getAsString());
            }
            return currencies;
        } catch (Exception e) {
            throw new RuntimeException(ErrorMessages.JSON_PARSING_ERROR + e.getMessage(), e);
        }
    }

    public static ExchangeRate parseExchangeRates(String jsonResponse) {
        JsonObject jsonObject = JsonParser.parseString(jsonResponse).getAsJsonObject();
        ExchangeRate rate = new ExchangeRate();
        rate.setBase(jsonObject.get("base").getAsString());
        rate.setDate(jsonObject.get("date").getAsString());
        
        JsonObject rates = jsonObject.getAsJsonObject("rates");
        Map<String, Double> rateMap = new HashMap<>();
        for (String currency : rates.keySet()) {
            rateMap.put(currency, rates.get(currency).getAsDouble());
        }
        rate.setRates(rateMap);
        return rate;
    }
}