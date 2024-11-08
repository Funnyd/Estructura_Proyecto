package com.mycompany.proyectoestructura;


//IMPORTS UTILIZADOS//
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

/**
 *
 * @author Kyo
 */


public class Configuracion {
    private String NombreBanco;
    private int CajaPreferencial;
    private int CantidadCajas;
    private int CajaRapida;
    
    
    //------------------------Metodos------------------------//
    //estoy revisando que se puede usar y que no
    //cualquier cosa si alguna libreria no cumple con los requisitos
    //diganme para arreglarlo y ver como hacerlo de otra manera
    
    public void Configuracion_LOAD(){
/*
        Este metodo lo que esta supuesto a hacer es cargar la configuracion guardada en "Save.tx"
        si existe tendria que cargar los datos, si no deberia solicitar la info que se guardara en el save
        su funcionalidad esta dudosa, mas que me faltaria la logica de otro metodo y verificar, si no lo hare desde 0

*/

        File CONF_FILE = new File("prod.txt");

        if (CONF_FILE.exists()) {
            
            try (BufferedReader br = new BufferedReader(new FileReader(CONF_FILE))) {
                NombreBanco = br.readLine();
 
                CantidadCajas = Integer.parseInt(br.readLine());
                
                CajaPreferencial = Integer.parseInt(br.readLine());
                
                CajaRapida = Integer.parseInt(br.readLine());
                
            } catch (IOException e) 
            
            {
                System.out.println("Error al leer la configuración: " + e.getMessage());
            }
            
            
        } else { // Note: si lo prueban quiten de comentarios esta parte, igualmente no funciona todavia//
            /*
            Scanner scanner = new Scanner(System.in);
          
            System.out.print("nombre del banco: ");
            NombreBanco = scanner.nextLine();
            
            System.out.print("antidad de cajas: ");
            CantidadCajas = scanner.nextInt();
            
            System.out.print("numero de caja preferencial: ");
            CajaPreferencial = scanner.nextInt();
            
            System.out.print("numero de caja rápida: ");
            CajaRapida = scanner.nextInt();
            */

            //SE PODIA USAR ? "-", SI NO SOLO BORRAR EL "-"
            //ESTE NO FUNCIONA FALTA LA LOGICA EN EL METODO
            Configuracion_SAVE();
        }
        
        
        
    
    } //fin del meotodo Configuracion_LOAD
    
    //falta agregar la logica de este metodo para que el metodo de arriba funcione
    public void Configuracion_SAVE() {}
}