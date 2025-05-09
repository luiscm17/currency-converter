package com.luiscm.currencyexchange.repository;

import com.luiscm.currencyexchange.model.ConversionRecord;
import com.luiscm.currencyexchange.interfaces.IOHandlerInterface;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class InMemoryHistoryRepository implements HistoryRepository {
    private final List<ConversionRecord> history = new ArrayList<>();

    @Override
    public void addConversion(ConversionRecord record) {
        history.add(record);
    }

    @Override
    public void displayHistory(IOHandlerInterface ioHandler) {
        history.forEach(record -> ioHandler.showMessage(record.toString()));
    }

    @Override
    public List<ConversionRecord> getAllConversions() {
        return Collections.unmodifiableList(history);
    }
}