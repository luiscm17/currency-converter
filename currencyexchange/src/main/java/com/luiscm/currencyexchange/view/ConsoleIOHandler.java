package com.luiscm.currencyexchange.view;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Map;

import com.luiscm.currencyexchange.interfaces.IOHandlerInterface;

public class ConsoleIOHandler implements IOHandlerInterface {
    @Override
    public String readCurrency(String prompt) throws IOException {
        System.out.print(prompt);
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        return reader.readLine().toUpperCase().trim();
    }

    @Override
    public double readAmount(String prompt) throws IOException {
        while (true) {
            System.out.print(prompt);
            try {
                return Double.parseDouble(new BufferedReader(new InputStreamReader(System.in)).readLine());
            } catch (NumberFormatException e) {
                System.out.println("Error: Ingrese un número válido");
            }
        }
    }

    @Override
    public void showResult(String from, String to, double amount, double result) {
        System.out.println(String.format("%.2f %s = %.2f %s", amount, from, result, to));
    }

    @Override
    public void showMenu() {
        System.out.println("\n1. Realizar conversión");
        System.out.println("2. Ver historial");
        System.out.println("3. Monedas disponibles");
        System.out.println("4. Salir\n");
    }

    @Override
    public String readInput(String prompt) throws IOException {
        System.out.print(prompt);
        return new BufferedReader(new InputStreamReader(System.in)).readLine();
    }

    @Override
    public void showMessage(String message) {
        System.out.println(message);
    }

    @Override
    public void showError(String message) {
        System.err.println("Error: " + message);
    }

    @Override
    public void showError(Exception e) {
        System.err.println("Error: " + e.getMessage());
        e.printStackTrace();
    }

    @Override
    public void displayAvailableCurrencies(Map<String, String> currencies) {
        System.out.println("\nMonedas disponibles:");
        currencies.forEach((codigo, nombre) -> 
            System.out.println(String.format("%s - %s", codigo, nombre))
        );
    }
}