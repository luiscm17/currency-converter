package com.luiscm.currencyexchange.model;

import java.util.Map;

import com.google.gson.annotations.SerializedName;

public class ExchangeRate {
    @SerializedName("result")
    private String result;
    
    @SerializedName("time_last_update_utc")
    private String timeLastUpdateUtc;
        
    @SerializedName("base_code")
    private String baseCode;
    
    @SerializedName("conversion_rates")
    private Map<String, Double> conversionRates;

    // Getters y Setters
    public String getBaseCode() {
        return baseCode;
    }

    public void setBaseCode(String baseCode) {
        this.baseCode = baseCode;
    }

    public Map<String, Double> getConversionRates() {
        return conversionRates;
    }

    public void setConversionRates(Map<String, Double> conversionRates) {
        this.conversionRates = conversionRates;
    }
}