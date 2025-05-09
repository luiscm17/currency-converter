package com.luiscm.currencyexchange;

import com.luiscm.currencyexchange.view.CLIHandler;
import com.luiscm.currencyexchange.service.CurrencyConverter;
import com.luiscm.currencyexchange.service.CurrencyService;
import com.luiscm.currencyexchange.config.AppConfig;
import com.luiscm.currencyexchange.core.HistoryService;
import com.luiscm.currencyexchange.interfaces.IOHandlerInterface;

public class Main {
    public static void main(String[] args) {
        try {
            CurrencyService currencyService = AppConfig.currencyService();
            HistoryService historyService = AppConfig.historyService();
            IOHandlerInterface ioHandler = AppConfig.ioHandler();
            
            new CLIHandler(
                new CurrencyConverter(currencyService),
                historyService,
                ioHandler
            ).start();
        } catch (Exception e) {
            System.err.println("Error crítico: " + e.getMessage());
            System.exit(1);
        }
    }
}