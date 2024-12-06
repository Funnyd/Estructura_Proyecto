package com.mycompany.proyectoestructura;

import java.io.*;

public class Configuracion {
    private String bankName;
    private int numberOfCajas;
    private static final String CONFIG_FILE = "config.txt";

    public boolean Configuracion_LOAD() {
        try (BufferedReader reader = new BufferedReader(new FileReader(CONFIG_FILE))) {
            bankName = reader.readLine();
            numberOfCajas = Integer.parseInt(reader.readLine());
            return true;
        } catch (IOException e) {
            return false;
        }
    }

    public void Configuracion_SAVE() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(CONFIG_FILE))) {
            writer.write("BancoEjemplo\n3"); 
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}