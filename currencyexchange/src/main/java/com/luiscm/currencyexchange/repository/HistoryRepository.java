package com.luiscm.currencyexchange.repository;

import com.luiscm.currencyexchange.interfaces.IOHandlerInterface;
import com.luiscm.currencyexchange.model.ConversionRecord;
import java.util.List;

public interface HistoryRepository {
    void addConversion(ConversionRecord record);
    List<ConversionRecord> getAllConversions();
    void displayHistory(IOHandlerInterface ioHandler);
}