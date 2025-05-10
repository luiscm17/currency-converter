package com.luiscm.currencyexchange.util;

import com.google.gson.Gson;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.Properties;

import com.google.gson.JsonObject;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import java.util.ArrayList;

public class ConfigLoader {
    private static Properties properties;

    static {
        try {
            properties = loadProperties();
        } catch (IOException e) {
            throw new RuntimeException("Error inicializando configuración", e);
        }
    }

    private static Properties loadProperties() throws IOException {
        Properties prop = new Properties();
        prop.load(ConfigLoader.class.getResourceAsStream("/config.properties"));
        return prop;
    }

    public static String getProperty(String key) {
        return properties.getProperty(key);
    }

    public static List<String> getSupportedCurrencies() {
        try (FileReader reader = new FileReader("currencyexchange/data/supportcurrencies.json")) {
            Gson gson = new Gson();
            JsonObject jsonObject = gson.fromJson(reader, JsonObject.class);
            JsonArray supportedCodes = jsonObject.getAsJsonArray("supported_codes");
            List<String> currencies = new ArrayList<>();
            
            for (JsonElement codeElement : supportedCodes) {
                JsonArray codeArray = codeElement.getAsJsonArray();
                String code = codeArray.get(0).getAsString();
                String name = codeArray.get(1).getAsString();
                currencies.add(code + " - " + name);
            }
            return currencies;
        } catch (IOException e) {
            throw new RuntimeException("Error cargando divisas soportadas", e);
        }
    }
}