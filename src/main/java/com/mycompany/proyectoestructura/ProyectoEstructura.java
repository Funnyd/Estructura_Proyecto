/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package com.mycompany.proyectoestructura;

import static com.mycompany.proyectoestructura.Cola.asignarCaja;
import static com.mycompany.proyectoestructura.Cola.cajaAtendida;
import static com.mycompany.proyectoestructura.Cola.crearTiquete;
import static com.mycompany.proyectoestructura.Cola.guiarTiquete;
import javax.swing.JOptionPane;

/**
 *
 * @author dques
 */
public class ProyectoEstructura {

    public static void main(String[] args) {
        int exit = 0;
        Cola Caja1 = new Cola(3);
        Cola Caja2 = new Cola(5);
        Cola Caja3 = new Cola(4);
        Cola CajaP = new Cola(3);
        Cola CajaR = new Cola(3);
        JOptionPane.showMessageDialog(null, "Bienvenido al Banco ABC!");

        while (exit == 0) {
            int opt = Integer.parseInt(JOptionPane.showInputDialog("Por favor introduzca la acción que desea: \n"
                    + "(1) Ingresar un tiquete\n"
                    + "(2) Atender un tiquete\n"
                    + "(3) Generar un reporte\n"
                    + "(4) Salir"));

            switch (opt) {
                case 1 -> {
                    Tiquete ticket = crearTiquete();
                    int tipo = guiarTiquete(ticket.getTipo());
                    switch (tipo) {
                        case 1:
                            CajaP.ingresarTicket(ticket);
                            JOptionPane.showMessageDialog(null, "Por favor ingrese a la Caja Preferencial");
                            JOptionPane.showMessageDialog(null, CajaP);
                            break;

                        case 2:
                            CajaR.ingresarTicket(ticket);
                            JOptionPane.showMessageDialog(null, "Por favor ingrese a la Caja Rapida");
                            JOptionPane.showMessageDialog(null, CajaR);
                            break;
                        case 3:
                            int caja = asignarCaja(Caja1, Caja2, Caja3);
                            switch (caja) {
                                case 1:
                                    Caja1.ingresarTicket(ticket);
                                    JOptionPane.showMessageDialog(null, "Por favor ingrese a la Caja 1");
                                    JOptionPane.showMessageDialog(null, Caja1);
                                    break;
                                case 2:
                                    Caja2.ingresarTicket(ticket);
                                    JOptionPane.showMessageDialog(null, "Por favor ingrese a la Caja 2");
                                    JOptionPane.showMessageDialog(null, Caja2);
                                    break;
                                case 3:
                                    Caja3.ingresarTicket(ticket);
                                    JOptionPane.showMessageDialog(null, "Por favor ingrese a la Caja 3");
                                    JOptionPane.showMessageDialog(null, Caja3);
                                    break;

                            }

                            break;
                    }
                }
                case 2 -> {
                    int caja = cajaAtendida();
                    switch (caja) {
                        case 1:
                            CajaP.atiende();
                            JOptionPane.showMessageDialog(null, CajaP);
                            break;

                        case 2:
                            CajaR.atiende();
                            JOptionPane.showMessageDialog(null, CajaR);
                            break;

                        case 3:
                            Caja1.atiende();
                            JOptionPane.showMessageDialog(null, Caja1);
                            break;
                            
                        case 4:
                            Caja2.atiende();
                            JOptionPane.showMessageDialog(null, Caja2);
                            break;
                            
                        case 5:
                            Caja3.atiende();
                            JOptionPane.showMessageDialog(null, Caja3);
                            break;

                    }
                }
                case 3 -> {
                }
                case 4 ->
                    exit = 1;
            }
        }

    }
}
