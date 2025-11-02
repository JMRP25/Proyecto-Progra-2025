/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.pruebamodel;


import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author braya
 */
@Entity
@Table(name="Cliente")

public class cliente implements Serializable{
    @Id
    @Column(name="idcliente")
    private long idCliente;
    
    @Column(name="Nombre")
    private String Nombre;
    
    @Column(name="Direccion")
    private String Direccion;
    
    @Column(name="Telefono")
    private String Telefono;

    public long getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(long idCliente) {
        this.idCliente = idCliente;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String Nombre) {
        this.Nombre = Nombre;
    }

    public String getDireccion() {
        return Direccion;
    }

    public void setDireccion(String Direccion) {
        this.Direccion = Direccion;
    }

    public String getTelefono() {
        return Telefono;
    }

    public void setTelefono(String Telefono) {
        this.Telefono = Telefono;
    }

    @Override
    public String toString() {
        return "cliente{" + "idCliente=" + idCliente + ", Nombre=" + Nombre + ", Direccion=" + Direccion + ", Telefono=" + Telefono + '}';
    }
    
    
    
    
    
    
}
