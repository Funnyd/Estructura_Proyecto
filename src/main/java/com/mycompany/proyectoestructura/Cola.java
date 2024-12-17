package com.mycompany.proyectoestructura;


import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
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
    public Tiquete atiende() {
        String now = LocalTime.now().format(DateTimeFormatter.ofPattern("HH:mm"));
        Tiquete aux = null;
        if (this.esVacio()) {
            JOptionPane.showMessageDialog(null, "No hay nadie quien atender");
            return null; // Indica que no hay nadie en la cola
        } else {
            if (prim == ult) {
                prim.getTiquete().setHoraAtencion(now);
                JOptionPane.showMessageDialog(null, prim);
                aux = prim.getTiquete();
                prim = ult = null;
            } else {
                prim.getTiquete().setHoraAtencion(now);
                JOptionPane.showMessageDialog(null, prim);
                aux = prim.getTiquete();
                prim = prim.getSig();
            }
        }
        return aux; // Indica que se atendió correctamente
        
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
    
    // Funcion para asignar la caja con menos personas
    public static void personasDelante(Cola caja) {
        // Asignar a la caja con menos personas
        int personasFila = caja.contar()-1;
        if (personasFila == 0) {
            JOptionPane.showMessageDialog(null, "La caja esta vacia, es su turno para ser atendido");
        } else {
            JOptionPane.showMessageDialog(null, "Hay "+personasFila+" personas delante suyo, por favor espere su turno");;
        }
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
    
    
   
    //-------METODOS DE PRUEBA: PARA TRATAR DE GUARDAR Y CARGAR EL ESTADO DE LAS COLAS------------//
    //NOTA: asi al salir o cerrar el sistema si un tiquete quedo sin hacer se cargar y continua en espera
    //de ser atendido.
    
    public void GuardarColas(String fileName) {
    try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
        Nodo actual = prim; 
        while (actual != null) {
            Tiquete tiquete = actual.getTiquete();
            writer.write(tiquete.getNombre() + "," +
                         tiquete.getHoraCreacion() + "," +
                         tiquete.getTramite() + "," +
                         tiquete.getTipo() + "," +
                         tiquete.getEdad() + "," +
                         tiquete.getHoraAtencion() + "," +
                         tiquete.getId());
            writer.newLine(); 
            actual = actual.getSig(); 
        }
        System.out.println("Estado de la cola guardado en: " + fileName);
    } catch (IOException e) {
        
        System.err.println("Ocurrió un error en el guardado del archivo: " + e.getMessage());
    }
}
   
    
    
  public void CargarColas(String fileName) {
    File file = new File(fileName);
    if (!file.exists()) {
        try {
            file.createNewFile();
            
            System.out.println("Perfecto el archivo fue creado con exito: " + fileName);
            
        } catch (IOException e) {
            
            System.err.println("Parece que ocurrio un error creando el archivo:" + e.getMessage());
        }
        return; 
    }
    try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
        String linea;
        while ((linea = reader.readLine()) != null) {
            String[] partes = linea.split(",");
            Tiquete tiquete = new Tiquete(
                partes[0], partes[1], partes[2], partes[3],
                Integer.parseInt(partes[4]), partes[5],
                Long.parseLong(partes[6])
            );
            
            this.ingresarTicket(tiquete);
        }
        System.out.println("Estado cargado desde: " + fileName);
    } catch (IOException e) {
        
        System.err.println("Ocurrió un error al cargar el estado de las colas: " + e.getMessage());
        
    } catch (NumberFormatException e) {
        
        System.err.println("Por lo visto ocurrio un error de formato en los datos: " + e.getMessage());
    }
}
    
   
    
  
   
}
