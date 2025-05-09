package com.luiscm.currencyexchange.util;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class JsonResponseHandler {
    public static JsonArray parseSupportedCodes(String jsonResponse) {
        JsonObject jsonObject = JsonParser.parseString(jsonResponse).getAsJsonObject();
        
        if (!jsonObject.has("supported_codes")) {
            throw new RuntimeException("Estructura JSON inválida: falta supported_codes");
        }
        
        return jsonObject.getAsJsonArray("supported_codes");
    }
}