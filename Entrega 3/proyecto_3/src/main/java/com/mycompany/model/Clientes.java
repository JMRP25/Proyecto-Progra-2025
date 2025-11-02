package com.mycompany.model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Table;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

@Entity
@Table(name = "Clientes", catalog = "prograll24Grupo7A", schema = "dbo")
@NamedQueries({
    @NamedQuery(name = "Clientes.findAll", query = "SELECT c FROM Clientes c")})
public class Clientes implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_cliente", nullable = false)
    private Long idCliente;
    
    @Column(name = "nombre", nullable = false, length = 255)
    private String nombre;
    
    @Column(name = "apellido", length = 255)
    private String apellido;
    
    @Column(name = "nit", length = 50)
    private String nit;
    
    @Column(name = "telefono", length = 50)
    private String telefono;
    
    @Column(name = "direccion", length = 500)
    private String direccion;

    // Constructores
    public Clientes() {
    }

    public Clientes(Long idCliente, String nombre, String apellido, String nit, 
                    String telefono, String direccion) {
        this.idCliente = idCliente;
        this.nombre = nombre;
        this.apellido = apellido;
        this.nit = nit;
        this.telefono = telefono;
        this.direccion = direccion;
    }

    // Getters y Setters
    public Long getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(Long idCliente) {
        this.idCliente = idCliente;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getNit() {
        return nit;
    }

    public void setNit(String nit) {
        this.nit = nit;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    @Override
    public String toString() {
        return "Clientes{" + 
               "idCliente=" + idCliente + 
               ", nombre='" + nombre + '\'' + 
               ", apellido='" + apellido + '\'' + 
               ", nit='" + nit + '\'' + 
               ", telefono='" + telefono + '\'' + 
               ", direccion='" + direccion + '\'' + 
               '}';
    }
}