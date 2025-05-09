package com.luiscm.currencyexchange.core;

import java.util.List;

import com.luiscm.currencyexchange.interfaces.IOHandlerInterface;
import com.luiscm.currencyexchange.model.ConversionRecord;

public interface HistoryService {
    void addConversion(ConversionRecord record);
    List<ConversionRecord> getHistory();
    void showHistory(IOHandlerInterface ioHandler);
}