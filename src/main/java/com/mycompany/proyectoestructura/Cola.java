package com.mycompany.proyectoestructura;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import javax.swing.JOptionPane;

public class Cola {

    private Nodo prim, ult;
    private int maxSize;

    public Cola(int maxSize) {
        this.maxSize = maxSize;
    }

    public boolean esVacio() {
        return prim == null;
    }

    // Ingresar ticket a la cola, considerando el tamaño máximo permitido
    public int ingresarTicket(Tiquete tick) {
        if (contar() >= maxSize) {
            JOptionPane.showMessageDialog(null, "La cola ha alcanzado su límite.");
            return -1; // Indica que no se pudo agregar por límite de la cola
        }
        Nodo nuevo = new Nodo(tick);
        if (this.esVacio()) {
            prim = ult = nuevo;
        } else {
            ult.setSig(nuevo);
            ult = nuevo;
        }
        return 1; // Indica que se agregó correctamente
    }
    
    public int ingresarTicketEspecial(Tiquete tick) {
        if (contar() >= maxSize) {
            JOptionPane.showMessageDialog(null, "La cola ha alcanzado su límite.");
            return -1; // Indica que no se pudo agregar por límite de la cola
        }
        Nodo nuevo = new Nodo(tick);
        if (this.esVacio()) {
            prim = ult = nuevo;
        } else {
            ult.setSig(nuevo);
            ult = nuevo;
        }
        return 1; // Indica que se agregó correctamente
    }

    //Funcion para crear un tiquete
    public static Tiquete crearTiquete() {
        String nombre = JOptionPane.showInputDialog("Ingrese su nombre");
        String id = JOptionPane.showInputDialog("Ingrese su cedula");
        String edad = JOptionPane.showInputDialog("Ingrese su edad");
        String Tramite = elegirTramite();       
        String Tipo = elegirTipo();
        String now = LocalTime.now().format(DateTimeFormatter.ofPattern("HH:mm"));
        Tiquete tiquete = new Tiquete(nombre, now, Tramite, Tipo, Integer.parseInt(edad), "-1", Long.valueOf(id));
        return tiquete;
    }

    // Contar el número de personas en la cola
    public int contar() {
        Nodo aux = prim;
        int largo = 0;
        while (aux != null) {
            largo += 1;
            aux = aux.getSig();
        }
        return largo;
    }

    // Atender a la primera persona en la cola
    public int atiende() {
        if (this.esVacio()) {
            JOptionPane.showMessageDialog(null, "No hay nadie quien atender");
            return -1; // Indica que no hay nadie en la cola
        } else {
            if (prim == ult) {
                prim = ult = null;
            } else {
                prim = prim.getSig();
            }
        }
        return 1; // Indica que se atendió correctamente
    }

    // Funcion para asignar la caja con menos personas
    public static int asignarCaja(Cola caja1, Cola caja2, Cola caja3) {
        // Asignar a la caja con menos personas
        if (caja2.contar() < caja1.contar() && caja2.contar() < caja3.contar()) {
            return 2;
        } else if (caja3.contar() < caja1.contar()) {
            return 3;
        }
        return 1; // En caso de empate o caja1 sea menor
    }


    
    //Funcion para elegir el tramite del tiquete
    public static String elegirTramite() {
        int opt = Integer.parseInt(JOptionPane.showInputDialog("Elija su tramite: \n"
                + "(1) Depositos\n"
                + "(2) Retiros\n"
                + "(3) Cambio Divisas"));
        switch (opt) {
            case 1:
                return "Depositos";
            case 2:
                return "Retiros";
            case 3:
                return "Cambio de Divisas";
            default:
                JOptionPane.showMessageDialog(null, "Por favor ingrese una opcion valida");
                crearTiquete();
        }
        return null;
    }
    
    //Funcion para elegir el tipo de tramite del tiquete
    public static String elegirTipo() {
        int opt = Integer.parseInt(JOptionPane.showInputDialog("Elija su tipo de tramite: \n"
                + "(1) Preferencial\n"
                + "(2) Un tramite\n"
                + "(3) Dos o mas tramites"));
        switch (opt) {
            case 1:
                return "Preferencial";
            case 2:
                return "Un Tramite";
            case 3:
                return "Dos o mas Tramites";
            default:
                JOptionPane.showMessageDialog(null, "Por favor ingrese una opcion valida");
                crearTiquete();
        }
        return null;
    }
    
    //Funcion para averiguar a traves del TIPO, a que caja enviar el tiquete
    public static int guiarTiquete(String tipo) {
        if("Preferencial".equals(tipo)){
            return 1;
        }else if("Un Tramite".equals(tipo)){
            return 2;
        }else{
            return 3;
        }
    }
    
    //Funcion para elegir que tipo de caja se va a atender
    public static int cajaAtendida() {
        int opt = Integer.parseInt(JOptionPane.showInputDialog("Elija cual caja desea atender: \n"
                + "(1) Caja Preferencial\n"
                + "(2) Caja Rapida\n"
                + "(3) Caja 1\n"
                + "(4) Caja 2\n"
                + "(5) Caja 3"));
        return opt;
        }
    

    @Override
    public String toString() {
        String s = "";
        if (esVacio()) {
            
        } else {
            Nodo nodo = prim;
            while (nodo != null) {
                s += nodo + "\n";
                nodo = nodo.getSig();
            }
        }
        return s;
    }
}
