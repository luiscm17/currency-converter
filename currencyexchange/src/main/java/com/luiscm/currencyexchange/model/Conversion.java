package com.luiscm.currencyexchange.model;

import java.time.LocalDateTime;

public class Conversion {
    private final String monedaOrigen;
    private final String monedaDestino;
    private final double monto;
    private final double resultado;
    public Conversion(String monedaOrigen, String monedaDestino, double monto, double resultado) {
        this.monedaOrigen = monedaOrigen;
        this.monedaDestino = monedaDestino;
        this.monto = monto;
        this.resultado = resultado;
        LocalDateTime.now();
    }

    public String getMonedaOrigen() { return monedaOrigen; }
    public String getMonedaDestino() { return monedaDestino; }
    public double getMonto() { return monto; }
    public double getResultado() { return resultado; }

    @Override
    public String toString() {
        return String.format("%s a %s: %.2f %s = %.2f %s", monedaOrigen, monedaDestino, monto, monedaOrigen, resultado, monedaDestino);
    }
}