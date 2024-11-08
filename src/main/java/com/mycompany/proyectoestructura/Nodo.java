/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.proyectoestructura;

/**
 *
 * @author dques
 */
public class Nodo {
    private Tiquete tiquete;
    private Nodo sig;
    
    public Nodo (Tiquete dato){
        this.tiquete = dato;
    }
    
    public Tiquete getTiquete(){
        return tiquete;
    }
    
    public void setTiquete(Tiquete dato){
        this.tiquete=dato;
    }
    
    public Nodo getSig(){
        return sig;
    }
    
    public void setSig(Nodo sig){
        this.sig=sig;
    }
    
    @Override
    public String toString() {
        return "Tiquete: \n" +tiquete; 
    }
}
