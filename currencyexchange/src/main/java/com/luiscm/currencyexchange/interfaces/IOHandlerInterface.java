package com.luiscm.currencyexchange.interfaces;

import java.io.IOException;
import java.util.Map;

public interface IOHandlerInterface {
    String readCurrency(String prompt) throws IOException;
    double readAmount(String prompt) throws IOException;
    void showResult(String fromCurrency, String toCurrency, double amount, double result);
    void showError(Exception e);
    void showError(String message);
    void showMessage(String message);
    String readInput(String prompt) throws IOException;
    void showMenu();
    void displayAvailableCurrencies(Map<String, String> currencies);
}