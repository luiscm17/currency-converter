package com.luiscm.currencyexchange.view;

import com.luiscm.currencyexchange.service.CurrencyService;

import java.util.Scanner;

public class CLIHandler {
    private final Scanner scanner;

    public CLIHandler() {
        this.scanner = new Scanner(System.in);
    }

    public void start() {
        mostrarBienvenida();
        
        String monedaOrigen = leerMoneda("Ingrese moneda base (ej. USD): ");
        String monedaDestino = leerMoneda("Ingrese moneda objetivo (ej. EUR): ");
        double monto = leerMonto("Ingrese monto a convertir: ");
        
        try {
            double resultado = CurrencyService.convertCurrency(monto, monedaOrigen, monedaDestino);
            mostrarResultado(resultado);
        } catch (Exception e) {
            System.out.println("\nError: " + e.getMessage());
        }
    }

    private void mostrarBienvenida() {
        System.out.println("*** Sistema de Conversión de Divisas ***");
    }

    private String leerMoneda(String mensaje) {
        System.out.print(mensaje);
        return scanner.nextLine().toUpperCase();
    }

    private double leerMonto(String mensaje) {
        System.out.print(mensaje);
        return Double.parseDouble(scanner.nextLine());
    }

    private void mostrarResultado(double resultado) {
        System.out.printf("\nResultado de la conversión: %.2f\n\n", resultado);
    }
}