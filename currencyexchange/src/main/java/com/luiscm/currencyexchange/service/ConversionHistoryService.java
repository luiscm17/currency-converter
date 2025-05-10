package com.luiscm.currencyexchange.service;

import com.luiscm.currencyexchange.exception.ConfigLoadException;
import com.luiscm.currencyexchange.model.Conversion;
import java.util.List;
import java.util.Properties;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.io.*;
import java.util.ArrayList;

public class ConversionHistoryService {
    private List<Conversion> conversions = new ArrayList<>();
    private final Gson gson = new Gson();
    private final File historyFile;

    public ConversionHistoryService() throws ConfigLoadException {
        Properties prop = new Properties();
        try (InputStream input = getClass().getClassLoader().getResourceAsStream("config.properties")) {
            if (input != null) {
                prop.load(input);
            }
        } catch (IOException e) {
            throw new ConfigLoadException("Error cargando configuración: " + e.getMessage());
        }
        String filePath = prop.getProperty("history.file.path", "currencyexchange/data/conversions.json");
        historyFile = new File(filePath);
        historyFile.getParentFile().mkdirs();
        loadHistory();
    }

    public void saveConversion(Conversion conversion) {
        conversions.add(conversion);
        saveToFile();
    }

    public List<Conversion> getHistory() {
        return new ArrayList<>(conversions);
    }

    public void clearHistory() {
        conversions.clear();
        saveToFile();
    }

    private void loadHistory() {
        try (Reader reader = new FileReader(historyFile)) {
            conversions = gson.fromJson(reader, new TypeToken<List<Conversion>>(){}.getType());
        } catch (FileNotFoundException e) {
            System.out.println("Creando nuevo archivo de historial...");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void saveToFile() {
        try (Writer writer = new FileWriter(historyFile)) {
            gson.toJson(conversions, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}