package com.luiscm.currencyexchange.model;

import java.util.Map;

import com.google.gson.annotations.SerializedName;

public class ExchangeRate {
        

    @SerializedName("base")
    private String base;

    @SerializedName("rates")
    private Map<String, Double> rates;


    public String getBase() {
        return base;
    }

    public void setBase(String base) {
        this.base = base;
    }

    public Map<String, Double> getRates() {
        return rates;
    }

    public void setRates(Map<String, Double> rates) {
        this.rates = rates;
    }

    private String date;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}