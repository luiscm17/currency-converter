package com.luiscm.currencyexchange.view;

import com.luiscm.currencyexchange.model.Conversion;
import com.luiscm.currencyexchange.service.ConversionHistoryService;
import com.luiscm.currencyexchange.service.CurrencyService;

import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class CLIHandler {
    private final Scanner scanner;
    private final CurrencyService currencyService;
    private final ConversionHistoryService historyService;

    public CLIHandler(CurrencyService currencyService, ConversionHistoryService historyService) {
        this.scanner = new Scanner(System.in);
        this.currencyService = currencyService;
        this.historyService = historyService;
    }

    private void handleConversion() {
        String monedaOrigen = seleccionarDivisaBase();
        String monedaDestino = seleccionarDivisaObjetivo(monedaOrigen);
        double monto = leerMonto("Ingrese monto a convertir: ");

        try {
            double resultado = currencyService.convertCurrency(monto, monedaOrigen, monedaDestino);
            historyService.saveConversion(new Conversion(monedaOrigen, monedaDestino, monto, resultado));
            mostrarResultado(monedaOrigen, monedaDestino, monto, resultado);
        } catch (Exception e) {
            System.out.println("\nError: " + e.getMessage());
        }
    }

    private String seleccionarDivisaBase() {
        List<String> divisas = currencyService.getSupportedCurrencies();
        return seleccionarDivisa("Seleccione divisa base:", divisas);
    }

    private String seleccionarDivisaObjetivo(String monedaOrigen) {
        List<String> divisas = currencyService.getSupportedCurrencies()
            .stream()
            .filter(c -> !c.equals(monedaOrigen))
            .collect(Collectors.toList());
        return seleccionarDivisa("Seleccione divisa objetivo:", divisas);
    }

    private String seleccionarDivisa(String titulo, List<String> divisas) {
        mostrarMenuDivisas(titulo, divisas);
        return obtenerSeleccionValida(divisas);
    }

    private void mostrarMenuDivisas(String titulo, List<String> divisas) {
        System.out.println("\n*** " + titulo + " ***\n");
        for (int i = 0; i < divisas.size(); i++) {
            System.out.printf("%d. %s%n", i + 1, divisas.get(i));
        }
    }

    private String obtenerSeleccionValida(List<String> divisas) {
        while (true) {
            System.out.print("\nSeleccione una opción: ");
            try {
                int opcion = Integer.parseInt(scanner.nextLine());
                if (opcion >= 1 && opcion <= divisas.size()) {
                    return divisas.get(opcion - 1).split(" - ")[0];
                }
                System.out.println("Opción inválida. Intente nuevamente.");
            } catch (NumberFormatException e) {
                System.out.println("Entrada inválida. Debe ingresar un número.");
            }
        }
    }

    private void showHistory() {
        System.out.println("\n-- Historial de conversiones --\n");
        List<Conversion> historial = historyService.getHistory();
        
        if (historial.isEmpty()) {
            System.out.println("No hay conversiones registradas aún");
        } else {
            int contador = 1;
            for (Conversion conv : historial) {
                System.out.printf("%d. %s%n", contador++, conv);
            }
        }
    }

        private void handleClearHistory() {
        System.out.print("¿Está seguro que desea borrar todo el historial? (S/N): ");
        String respuesta = scanner.nextLine().trim().toLowerCase();
        if (respuesta.equals("s")) {
            historyService.clearHistory();
            System.out.println("Historial borrado exitosamente.");
        } else {
            System.out.println("Operación cancelada.");
        }
    }

    public void start() {
        boolean salir = false;
        
        while (!salir) {
            mostrarMenu();
            int opcion = leerOpcion();
            
            switch (opcion) {
                case 1:
                    handleConversion();
                    break;
                case 2:
                    showHistory();
                    break;
                case 3:
                    handleClearHistory();
                    break;
                case 4:
                    salir = exitApp();
                    break;
                default:
                    System.out.println("Opción inválida");
            }
        }
    }

    private void mostrarMenu() {
        System.out.println("\n*** MENU PRINCIPAL ***\n");
        System.out.println("1. Realizar conversión");
        System.out.println("2. Ver historial");
        System.out.println("3. Borrar historial");
        System.out.println("4. Salir\n");
    }

    private int leerOpcion() {
        System.out.print("Seleccione una opción: ");
        return Integer.parseInt(scanner.nextLine());
    }

    private double leerMonto(String mensaje) {
        while (true) {
            System.out.print(mensaje);
            String input = scanner.nextLine().trim();
            
            if (input.isEmpty()) {
                System.out.println("Error: El monto no puede estar vacío.");
                continue;
            }
            
            try {
                double monto = Double.parseDouble(input);
                if (monto <= 0) {
                    System.out.println("Error: El monto debe ser mayor que cero.");
                    continue;
                }
                return monto;
            } catch (NumberFormatException e) {
                System.out.println("Error: Por favor ingrese un valor numérico válido.");
            }
        }
    }

    private void mostrarResultado(String monedaOrigen, String monedaDestino, double monto, double resultado) {
    double tasa = resultado / monto;
    System.out.printf("\n%.2f %s equivalen a %.2f %s (Tasa: 1 %s = %.4f %s)\n\n", 
        monto, monedaOrigen, resultado, monedaDestino, monedaOrigen, tasa, monedaDestino);
}

    private boolean exitApp() {
        System.out.println("\n¡Hasta luego!");
        scanner.close();
        return true;
    }
}