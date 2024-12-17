/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.proyectoestructura;


import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import javax.swing.JOptionPane;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author joel
 */

public class Reportes {
    
    
    private static Map<String, Integer> tiempoTotalPorCaja = new HashMap<>();
    private static Map<String, Integer> clientesPorCaja = new HashMap<>();
 
    
    private static int TotalClientes =  0 ;
    private static int ClientesPRFL  =  0 ;
    private static int ClientesCJRP  =  0 ;
    private static int ClientesCJ1   =  0 ;
    private static int ClientesCJ2   =  0 ;
    private static int ClientesCJ3   =  0 ;
       

    
    
    
       //-----------METODO PARA AGREGAR DATOS A LAS VARIABLES DE LAS CAJAS-------------//
    public static void VER ( int caja ){
    switch(caja){
        case 1:
        TotalClientes++;
        ClientesPRFL++;
        break;
            
        case 2:
        TotalClientes++;
        ClientesCJRP++;
        break;
                 
        case 3:
        TotalClientes++;
        ClientesCJ1++;
        break;
                     
        case 4:
        TotalClientes++;
        ClientesCJ2++;
        break;
                           
        case 5:
        TotalClientes++;
        ClientesCJ3++;
        break;
    }
}
    
    
    
    

        //-----------METODO PARA OBTENER LA CAJA CON MAYOR NUMERO DE CLIENTES-------------//
    public static String MayorCNT() {
    int MAY = ClientesPRFL;
    String resultado = "ClientesPRFL";

    if (ClientesCJRP > MAY) {
        MAY = ClientesCJRP;
        resultado = "ClientesCJRP";
    }
    if (ClientesCJ1 > MAY) {
        MAY = ClientesCJ1;
        resultado = "ClientesCJ1";
    }
    if (ClientesCJ2 > MAY) {
        MAY = ClientesCJ2;
        resultado = "ClientesCJ2";
    }
    if (ClientesCJ3 > MAY) {
        MAY = ClientesCJ3;
        resultado = "ClientesCJ3";
    }

    String mensaje = "La caja con más clientes es: " + resultado + " con " + MAY + " clientes.\n"
            + "Total de clientes atendidos: " + TotalClientes;
    return mensaje;
}
  



    
    public static void registrarTiempo(String caja, int tiempo) {
        tiempoTotalPorCaja.put(caja, tiempoTotalPorCaja.getOrDefault(caja, 0) + tiempo);
        clientesPorCaja.put(caja, clientesPorCaja.getOrDefault(caja, 0) + 1);
    }

    
    
    
    
    
    public static String obtenerCajaConMejorTiempoPromedio() {
        String mejorCaja = null;
        double mejorPromedio = Double.MAX_VALUE;

        for (String caja : tiempoTotalPorCaja.keySet()) {
            int totalTiempo = tiempoTotalPorCaja.get(caja);
            int totalClientes = clientesPorCaja.getOrDefault(caja, 1);

            double promedio = (double) totalTiempo / totalClientes;
            if (promedio < mejorPromedio) {
                mejorPromedio = promedio;
                mejorCaja = caja;
            }
        }

        return mejorCaja != null ? "La caja con el mejor tiempo promedio es " + mejorCaja +
                " con un promedio de " + mejorPromedio + " minutos." : "No hay datos disponibles.";
    }

    
    
    
    
    
 
    public static String obtenerTiempoPromedioGeneral() {
        int tiempoTotal = 0;
        int totalClientes = 0;

        for (String caja : tiempoTotalPorCaja.keySet()) {
            tiempoTotal += tiempoTotalPorCaja.get(caja);
            totalClientes += clientesPorCaja.getOrDefault(caja, 0);
        }

        if (totalClientes == 0) {
            return "No hay clientes atendidos para calcular el promedio general.";
        }

        double promedioGeneral = (double) tiempoTotal / totalClientes;
        return "El tiempo promedio general de atencion es " + promedioGeneral + " minutos.";
    }

    
    
 

    
    //-----------METODO REGISTRAR ACCIONES DE LAS CAJAS-------------//
    public static void registrarAccion(String cajaNombre) {  
    try (BufferedWriter writer = new BufferedWriter ( new FileWriter("Reporte.txt", true)))
    {
    String registro = String.format("Caja: %s atendio a un cliente\n", cajaNombre);
    writer.write(registro);     
    } catch (IOException e){
    JOptionPane.showMessageDialog(null, "Ocurrio un error al intentar guardar el registro: " + e.getMessage());
    }
}

    
    //-----------METODO PARA MOSTRAR LA INFORMACION DE REPORTES DE LA CAJA--------------//
    public static void REPORTES_CAJAS(Cola CajaP, Cola CajaR, Cola Caja1, Cola Caja2, Cola Caja3) {
    while (true) {
        int opcion = Integer.parseInt(JOptionPane.showInputDialog(
            "Seleccione una opcion del reporte:\n" +
            "1. Reporte cual caja atendio la mayor cantidad de .\n" +
            "2. Reporte de el total de clientes atendidos.\n" +
            "3. Reporte de cual caja tiene el mejor tiempo de atencion promedio.\n" +
            "4. Reporte de cual es el tiempo promedio de atención en general (todas las cajass).\n" +
            "5. Salir del reporte."
        ));

        switch (opcion) {
            case 1 -> {
                JOptionPane.showMessageDialog(null, "Opción 1 seleccionada: Cuál caja atendió mayor cantidad de clientes.");
                String mensaje = MayorCNT();
                JOptionPane.showMessageDialog(null, mensaje, "Resultados", JOptionPane.INFORMATION_MESSAGE);
 
                break;
            }
            case 2 -> {
                JOptionPane.showMessageDialog(null, "Opción 2 seleccionada: El total de clientes atendidos fue de : " + TotalClientes);
            }
            case 3 -> {
                JOptionPane.showMessageDialog(null, "");
            }
            case 4 -> {
                JOptionPane.showMessageDialog(null, "");
            }
            case 5 -> {
                JOptionPane.showMessageDialog(null, "Saliendo del reporte.");
                return; 
            }
            default -> {
                JOptionPane.showMessageDialog(null, "Opcion no valida.");
            }
        }
    }
}




    
    
    
    
}//----------------FIN DE LA CLASE REPORTES-----------------//


