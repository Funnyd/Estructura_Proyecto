/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package com.mycompany.proyectoestructura;

//Imports de la clase Cola
import static com.mycompany.proyectoestructura.Cola.asignarCaja;
import static com.mycompany.proyectoestructura.Cola.cajaAtendida;
import static com.mycompany.proyectoestructura.Cola.crearTiquete;
import static com.mycompany.proyectoestructura.Cola.guiarTiquete;
import static com.mycompany.proyectoestructura.Cola.personasDelante;
import static com.mycompany.proyectoestructura.Configuracion.guardarTiquete;

//Imports de la clase configuracion
import static com.mycompany.proyectoestructura.Configuracion.registrarUsuario;
import static com.mycompany.proyectoestructura.Configuracion.verificarArchivoUsuarios;
import static com.mycompany.proyectoestructura.Configuracion.verificarUsuario;

//Imports de la clase reporte
import static com.mycompany.proyectoestructura.Reportes.REPORTES_CAJAS;
import static com.mycompany.proyectoestructura.Reportes.VER;
import static com.mycompany.proyectoestructura.Reportes.registrarAccion;

import javax.swing.JOptionPane;

/**
 *
 * @author dques
 */
public class ProyectoEstructura {

    public static void main(String[] args) {
        if (menuLogin()) {
            ejecutarSistema();
        } else {
            System.out.println("Acceso denegado. Saliendo del sistema.");
        }

    }

    //Metodo de verificacion
    public static boolean menuLogin() {
        while (true) {
            int opcion = Integer.parseInt(JOptionPane.showInputDialog(
                    "Bienvenido al sistema del Banco, Seleccione una opcion\n"
                    + "Seleccione una opción:\n"
                    + "(1) Log in\n"
                    + "(2) Register\n"
                    + "(3) Salir"
            ));

            switch (opcion) {
                case 1 -> {
                    String usuario = JOptionPane.showInputDialog("Ingrese su usuario:");

                    String contrasena = JOptionPane.showInputDialog("Ingrese su contraseña:");
                    if (verificarUsuario(usuario, contrasena)) {
                        JOptionPane.showMessageDialog(null, "Inicio de sesion exitoso. Bienvenido " + usuario + "!");
                        return true;
                    } else {
                        JOptionPane.showMessageDialog(null, "Usuario o contraseña incorrectos.");
                    }
                }

                case 2 -> {
                    String adminUsuario = JOptionPane.showInputDialog("Ingrese usuario administrador:");
                    String adminContrasena = JOptionPane.showInputDialog("Ingrese contraseña de administrador:");
                    if (adminUsuario.equals("admin") && adminContrasena.equals("123")) {
                        String nuevoUsuario = JOptionPane.showInputDialog("Ingrese el nuevo usuario:");
                        String nuevaContrasena = JOptionPane.showInputDialog("Ingrese la contraseña:");
                        if (registrarUsuario(nuevoUsuario, nuevaContrasena)) {
                            JOptionPane.showMessageDialog(null, "Usuario registrado con exito.");
                        } else {
                            JOptionPane.showMessageDialog(null, "Error al registrar el usuario.");
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "Credenciales de administrador incorrectas.");
                    }
                }

                case 3 -> {
                    JOptionPane.showMessageDialog(null, "Saliendo del sistema.");
                    System.exit(0);
                }
                default ->
                    JOptionPane.showMessageDialog(null, "Opcion no valida.");
            }
        }

    }

    //Metodo main, convertido a metodo
    public static void ejecutarSistema() {
        int exit = 0;
        Tiquete tiqueteBD = null;
        Cola Caja1 = new Cola(3);
        Cola Caja2 = new Cola(5);
        Cola Caja3 = new Cola(4);
        Cola CajaP = new Cola(3);
        Cola CajaR = new Cola(3);

        CajaP.CargarColas("CajaPreferencial.txt");
        CajaR.CargarColas("CajaRapida.txt");
        Caja1.CargarColas("Caja1.txt");
        Caja2.CargarColas("Caja2.txt");
        Caja3.CargarColas("Caja3.txt");

        while (exit == 0) {
            int opt = Integer.parseInt(JOptionPane.showInputDialog("Por favor introduzca la accion que desea: \n"
                    + "(1) Ingresar un tiquete\n"
                    + "(2) Atender un tiquete\n"
                    + "(3) Generar un reporte\n"
                    + "(4) Salir"));

            switch (opt) {
                case 1 -> {
                    Tiquete ticket = crearTiquete();
                    int tipo = guiarTiquete(ticket.getTipo());
                    switch (tipo) {
                        case 1 -> {
                            CajaP.ingresarTicket(ticket);
                            JOptionPane.showMessageDialog(null, "Por favor ingrese a la Caja Preferencial");
                            JOptionPane.showMessageDialog(null, ticket);
                            personasDelante(CajaP);
                        }
                        case 2 -> {
                            CajaR.ingresarTicket(ticket);
                            JOptionPane.showMessageDialog(null, "Por favor ingrese a la Caja Rapida");
                            JOptionPane.showMessageDialog(null, ticket);
                            personasDelante(CajaR);
                        }
                        case 3 -> {
                            int caja = asignarCaja(Caja1, Caja2, Caja3);
                            switch (caja) {
                                case 1 -> {
                                    Caja1.ingresarTicket(ticket);
                                    JOptionPane.showMessageDialog(null, "Por favor ingrese a la Caja 1");
                                    JOptionPane.showMessageDialog(null, ticket);
                                    personasDelante(Caja1);
                                }
                                case 2 -> {
                                    Caja2.ingresarTicket(ticket);
                                    JOptionPane.showMessageDialog(null, "Por favor ingrese a la Caja 2");
                                    JOptionPane.showMessageDialog(null, ticket);
                                    personasDelante(Caja2);
                                }
                                case 3 -> {
                                    Caja3.ingresarTicket(ticket);
                                    JOptionPane.showMessageDialog(null, "Por favor ingrese a la Caja 3");
                                    JOptionPane.showMessageDialog(null, ticket);
                                    personasDelante(Caja3);
                                }
                            }
                        }
                    }
                }
                case 2 -> {
                    int caja = cajaAtendida();

                    switch (caja) {
                        case 1 -> {
                            tiqueteBD = CajaP.atiende();
                            VER(1);
                            registrarAccion("Caja Preferencial");
                            guardarTiquete(tiqueteBD);

                        }

                        case 2 -> {
                            tiqueteBD = CajaR.atiende();
                            VER(2);
                            registrarAccion("Caja Rapida");
                            guardarTiquete(tiqueteBD);
                        }
                        case 3 -> {
                            tiqueteBD = Caja1.atiende();
                            VER(3);
                            registrarAccion("Caja 1");
                            guardarTiquete(tiqueteBD);
                        }
                        case 4 -> {
                            tiqueteBD = Caja2.atiende();
                            VER(4);
                            registrarAccion("Caja 2");
                            guardarTiquete(tiqueteBD);
                        }
                        case 5 -> {
                            tiqueteBD = Caja3.atiende();
                            VER(5);
                            registrarAccion("Caja 3");
                            guardarTiquete(tiqueteBD);
                        }
                    }
                }
                case 3 -> {
                    REPORTES_CAJAS(CajaP, CajaR, Caja1, Caja2, Caja3);
                }
                case 4 -> {

                    CajaP.GuardarColas("CajaPreferencial.txt");
                    CajaR.GuardarColas("CajaRapida.txt");
                    Caja1.GuardarColas("Caja1.txt");
                    Caja2.GuardarColas("Caja2.txt");
                    Caja3.GuardarColas("Caja3.txt");

                    exit = 1;

                }
            }

        }

    }

}
