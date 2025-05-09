package com.luiscm.currencyexchange.view;

import java.io.IOException;

import com.luiscm.currencyexchange.exception.DataProviderException;
import com.luiscm.currencyexchange.exception.InvalidCurrencyException;
import com.luiscm.currencyexchange.model.ConversionRecord;
import com.luiscm.currencyexchange.interfaces.CurrencyConverterInterface;
import com.luiscm.currencyexchange.core.HistoryService;
import com.luiscm.currencyexchange.interfaces.IOHandlerInterface;

public class CLIHandler {
    private final CurrencyConverterInterface currencyConverter;
    private final HistoryService historyService;
    private final IOHandlerInterface ioHandler;

    public CLIHandler(CurrencyConverterInterface converter,
            HistoryService historyService,
            IOHandlerInterface ioHandler) {
        this.currencyConverter = converter;
        this.historyService = historyService;
        this.ioHandler = ioHandler;
    }

    private void realizarConversion() throws InterruptedException, IOException {
        try {
            String monedaOrigen = ioHandler.readCurrency("Moneda origen (ej. USD): ");
            String monedaDestino = ioHandler.readCurrency("Moneda destino (ej. EUR): ");
            double monto = ioHandler.readAmount("Monto a convertir: ");

            double resultado = currencyConverter.convert(monto, monedaOrigen, monedaDestino);
            historyService.addConversion(new ConversionRecord(monto, monedaOrigen, monedaDestino, resultado));
            ioHandler.showResult(monedaOrigen, monedaDestino, monto, resultado);
        } catch (InvalidCurrencyException | DataProviderException e) {
            ioHandler.showError(e);
        }
    }

    public void start() throws InterruptedException, IOException {
        boolean ejecutando = true;
        while (ejecutando) {
            ioHandler.showMenu();
            String opcion = ioHandler.readInput("Seleccione una opción: ");

            switch (opcion) {
                case "1":
                    realizarConversion();
                    break;
                case "2":
                    historyService.showHistory(ioHandler);
                    break;
                case "3":
                    try {
                        currencyConverter.showAvailableCurrencies(ioHandler);
                    } catch (DataProviderException e) {
                        ioHandler.showError(e);
                    }
                    break;
                case "4":
                    ejecutando = false;
                    ioHandler.showMessage("Saliendo del sistema...");
                    break;
                default:
                    ioHandler.showError("Opción no válida");
            }
        }
    }
}