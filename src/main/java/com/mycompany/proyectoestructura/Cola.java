
package com.mycompany.proyectoestructura;

import javax.swing.JOptionPane;

public class Cola {

    private Nodo prim, ult;
    private int maxSize;

    // Constructor to set the maximum size of the queue based on the box (Caja)
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

    // Configurar reglas para asignación de cajas
    public static int asignarCaja(Cola caja1, Cola caja2, Cola caja3) {
        // Asignar a la caja con menos personas
        if (caja2.contar() < caja1.contar() && caja2.contar() < caja3.contar()) {
            return 2;
        } else if (caja3.contar() < caja1.contar()) {
            return 3;
        }
        return 1; // En caso de empate o caja1 sea menor
    }

    // Método especial para asignación en cajas rápidas o preferenciales
    public static boolean esCajaEspecial(Tiquete tick) {
        return "P".equals(tick.getTipo());
    }
    
    public static boolean esCajaRapida(Tiquete tick) {
        return "A".equals(tick.getTipo());
    }
    
        @Override
    public String toString(){
        String s = "";
        if(esVacio()){
            s="Cola Vacia";
        }else{
            Nodo nodo = prim;
            while(nodo!=null){
                s+= nodo+"\n";
                nodo = nodo.getSig();
            }
        }
        return s;
    }
}
