package com.luiscm.currencyexchange.service;

import com.luiscm.currencyexchange.model.ConversionRecord;
import com.luiscm.currencyexchange.repository.HistoryRepository;

import java.util.List;

public class ConversionHistoryService {
    private final HistoryRepository repository;

        public ConversionHistoryService(HistoryRepository repository) {
        this.repository = repository;
    }

    public void addConversion(ConversionRecord record) {
        repository.addConversion(record);
    }

    public List<ConversionRecord> getHistory() {
        return repository.getAllConversions();
    }

    public void logConversion(double amount, String baseCurrency, String targetCurrency, double result) {
        // Lógica para registrar la conversión
        ConversionRecord record = new ConversionRecord(amount, baseCurrency, targetCurrency, result);
        repository.addConversion(record);
    }
}