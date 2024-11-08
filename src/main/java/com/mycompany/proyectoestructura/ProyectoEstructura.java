/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package com.mycompany.proyectoestructura;

import javax.swing.JOptionPane;

/**
 *
 * @author dques
 */
public class ProyectoEstructura {

    public static void main(String[] args) {
        Interfaz CV = new Interfaz(); 
        CV.show(); 
        Cola Caja1 = new Cola(3);
        Cola Caja2 = new Cola(5);
        Cola Caja3 = new Cola(4);
        Cola CajaP = new Cola(3);

        
        Tiquete Juan = new Tiquete("Juan", "10:00", "Deposito", "P", 30, -1, 1L);
        Tiquete Paula = new Tiquete("Paula", "08:00", "Retiro", "A", 23, -1, 2L);
        Tiquete Gerald = new Tiquete("Gerald", "12:00", "Cambio Divisas", "B", 30, -1, 3L);

    }
}
