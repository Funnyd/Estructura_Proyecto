package com.mycompany.proyectoestructura;

import java.io.*;
import javax.swing.JOptionPane;

/**
 *
 * @author joel
 */

public class Configuracion {
    private String bankName;
    private int numberOfCajas;
    private static final String CONFIG_FILE = "config.txt";
    
    

    //---------------METODO CARGAR CONFIGURACION---------------//
    public boolean Configuracion_LOAD() {
    try (BufferedReader reader = new BufferedReader(new FileReader(CONFIG_FILE))) {
        bankName = reader.readLine();
        numberOfCajas = Integer.parseInt(reader.readLine());
        return true;
    } catch (IOException e) {
        System.err.println("Error al leer el archivo de configuración: " + e.getMessage());
        return false;
    } catch (NumberFormatException e) {
        System.err.println("El número de cajas en el archivo de configuración es inválido.");
        return false;
    }
}
    
    
    
    
    //---------METODO GUARDAR CONFIGURACION------------//
    public void Configuracion_SAVE(String bankName, int numberOfCajas) {
    try (BufferedWriter writer = new BufferedWriter(new FileWriter(CONFIG_FILE))) {
        writer.write(bankName);
        writer.newLine();
        writer.write(String.valueOf(numberOfCajas));
        System.out.println("Configuración guardada correctamente.");
    } catch (IOException e) {
        System.err.println("Error al guardar la configuración: " + e.getMessage());
    }
}
    
    
    
    
//-----------------METODO PARA VERIFICAR SI EL USUARIO ADMINISTRADOR EXISTE-----------//

    public static void verificarArchivoUsuarios() {
    File archivo = new File("usuarios.txt");
    if (!archivo.exists() || archivo.length() == 0) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(archivo))) {
            writer.write("admin,123");
            writer.newLine();
            System.out.println("Archivo usuarios.txt creado con el usuario admin predeterminado.");
        } catch (IOException e) {
            System.err.println("Error al crear el archivo de usuarios: " + e.getMessage());
        }
    }
}
     

    //--------------------------METODO PARA VERIFICAR  EN EL ARCHIVO-------------------------//
    public static boolean verificarUsuario(String usuario, String pass) {
    try (BufferedReader reader = new BufferedReader(new FileReader("usuarios.txt"))) {
        String linea;
        while ((linea = reader.readLine()) != null) {
            String[] datos = linea.split(",");
            if (datos[0].equals(usuario) && datos[1].equals(pass)) { 
                return true;
            }
        }
    } catch (IOException e) {
        System.err.println("Sucedio un error al leer usuarios: " + e.getMessage());
    }
    return false;
}
     
     

    
        //--------------------------METODO PARA REGISTRAR USUARIOS-------------------------//

    public static boolean registrarUsuario(String usuario, String pass) {
    if (verificarUsuario(usuario, pass)) {
        JOptionPane.showMessageDialog(null, "El usuario que tratas de agregar ya existe.");
        return false;
    }
    try (BufferedWriter writer = new BufferedWriter(new FileWriter("usuarios.txt", true))) {
        writer.write(usuario + "," + pass); 
        writer.newLine();
        return true;
    } catch (IOException e) {
        System.err.println("Error al registrar usuario: " + e.getMessage());
    }
    return false;
}
    
    
        //--------------------------METODO PARA GUARDAR TIQUETES-------------------------//

    public static boolean guardarTiquete(Tiquete tiquete) {
    try (BufferedWriter writer = new BufferedWriter(new FileWriter("tiquetes.txt", true))) {
        writer.write(tiquete.toString()); 
        writer.newLine();
        return true;
    } catch (IOException e) {
        System.err.println("Error al guardar tiquete: " + e.getMessage());
    }
    return false;
}
    
    
    
    
    
    
    
}