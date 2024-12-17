/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.proyectoestructura;


/**
 *
 * @author dques
 */
public class Tiquete {
    private String nombre, tramite, tipo;
    private int edad ;
    private String horaCreacion, horaAtencion;
    private Long id;

    
    public Tiquete() {
        
    }

    public Tiquete(String nombre, String horaCreacion, String tramite, String tipo, int edad, String horaAtencion, Long id) {
        this.nombre = nombre;
        this.horaCreacion = horaCreacion;
        this.tramite = tramite;
        this.tipo = tipo;
        this.edad = edad;
        this.horaAtencion = horaAtencion;
        this.id = id;
    }

    

    public String getNombre() {
        return nombre;
    }

    public String getHoraCreacion() {
        return horaCreacion;
    }

    public int getEdad() {
        return edad;
    }

    public String getHoraAtencion() {
        return horaAtencion;
    }

    public Long getId() {
        return id;
    }

    public String getTramite() {
        return tramite;
    }

    public String getTipo() {
        return tipo;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setHoraCreacion(String horaCreacion) {
        this.horaCreacion = horaCreacion;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public void setHoraAtencion(String horaAtencion) {
        this.horaAtencion = horaAtencion;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setTramite(String tramite) {
        this.tramite = tramite;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
    
    @Override
    public String toString(){
        return "Nombre:"+nombre + "\n" +
               "ID:"+id + "\n" +
               "Edad:"+edad + "\n" +
               "Hora de Creación:"+horaCreacion + "\n" +
               "Hora de Atencion:"+horaAtencion + "\n" +
               "Tramite:"+tramite + "\n" +
               "Tipo:"+tipo + "\n";
                
                 
                
    }
    
}
