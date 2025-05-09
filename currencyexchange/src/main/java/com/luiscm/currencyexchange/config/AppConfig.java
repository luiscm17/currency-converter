package com.luiscm.currencyexchange.config;

import com.luiscm.currencyexchange.provider.FixerCurrencyProvider;
import com.luiscm.currencyexchange.repository.HistoryRepository;
import com.luiscm.currencyexchange.repository.InMemoryHistoryRepository;

import java.io.IOException;

import com.luiscm.currencyexchange.core.HistoryService;
import com.luiscm.currencyexchange.exception.DataProviderException;
import com.luiscm.currencyexchange.infraestructure.HistoryServiceImpl;
import com.luiscm.currencyexchange.interfaces.IOHandlerInterface;
import com.luiscm.currencyexchange.service.CurrencyService;
import com.luiscm.currencyexchange.view.ConsoleIOHandler;

public class AppConfig {
    public static HistoryRepository historyRepository() {
        return new InMemoryHistoryRepository();
    }
    
    public static HistoryService historyService() {
        return new HistoryServiceImpl(historyRepository());
    }
    
    public static CurrencyService currencyService() throws IOException, DataProviderException {
        FixerCurrencyProvider provider = new FixerCurrencyProvider();
        return new CurrencyService(provider, provider);
    }
    
    public static IOHandlerInterface ioHandler() {
        return new ConsoleIOHandler();
    }
}