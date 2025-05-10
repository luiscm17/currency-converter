package com.luiscm.currencyexchange;

import com.luiscm.currencyexchange.client.ApiClient;
import com.luiscm.currencyexchange.exception.ConfigLoadException;
import com.luiscm.currencyexchange.service.CurrencyService;
import com.luiscm.currencyexchange.util.CurrencyValidator;
import com.luiscm.currencyexchange.view.CLIHandler;
import com.luiscm.currencyexchange.service.ConversionHistoryService;

public class Main {
    public static void main(String[] args) {
        ApiClient apiClient = new ApiClient();
        CurrencyService currencyService = new CurrencyService(apiClient, new CurrencyValidator());
        try {
            ConversionHistoryService historyService = new ConversionHistoryService();
            new CLIHandler(currencyService, historyService).start();
        } catch (ConfigLoadException e) {
            System.err.println("Error cargando configuración: " + e.getMessage());
            System.exit(1);
        }
    }
}