package com.luiscm.currencyexchange.provider;

import com.luiscm.currencyexchange.exception.DataProviderException;
import java.util.Map;

public interface CurrencyListProvider {
    Map<String, String> getAvailableCurrencies() throws DataProviderException;
}