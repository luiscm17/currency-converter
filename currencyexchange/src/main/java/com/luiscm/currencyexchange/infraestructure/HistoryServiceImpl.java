package com.luiscm.currencyexchange.infraestructure;

import com.luiscm.currencyexchange.core.HistoryService;
import com.luiscm.currencyexchange.interfaces.IOHandlerInterface;
import com.luiscm.currencyexchange.model.ConversionRecord;
import com.luiscm.currencyexchange.repository.HistoryRepository;

import java.util.List;

public class HistoryServiceImpl implements HistoryService {
    private final HistoryRepository repository;

    public HistoryServiceImpl(HistoryRepository repository) {
        this.repository = repository;
    }

    @Override
    public void addConversion(ConversionRecord record) {
        repository.addConversion(record);
    }

    @Override
    public List<ConversionRecord> getHistory() {
        return repository.getAllConversions();
    }

    @Override
    public void showHistory(IOHandlerInterface ioHandler) {
        List<ConversionRecord> records = getHistory();
        if (records.isEmpty()) {
            ioHandler.showMessage("\nNo hay registros en el historial.");
            return;
        }
        
        ioHandler.showMessage("\nHistorial de conversiones:");
        for (ConversionRecord record : records) {
            ioHandler.showMessage(String.format(
                "%s -> %s: %.2f → %.2f | %s",
                record.getFromCurrency(),
                record.getToCurrency(),
                record.getAmount(),
                record.getResult(),
                record.getTimestamp().toString()
            ));
        }
    }
}