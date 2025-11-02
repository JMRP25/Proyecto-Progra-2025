/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador;

import javax.enterprise.context.RequestScoped;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import modelo.cliente;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

@RequestScoped
public class ClienteRepositorio implements PanacheRepository<cliente> {

    @PersistenceContext
    EntityManager em;

    //  crear un nuevo cliente
    public void create(cliente cliente) {
        persist(cliente); // Usamos Panache para persistir el cliente
    }

    //  buscar un cliente por ID
    public cliente findById(String idCliente) {
        return em.find(cliente.class, idCliente); // Busca por ID usando EntityManager
    }

    // obtener todos los clientes
    public List<cliente> findAllClientes() {
        return listAll(); // Utiliza Panache para obtener todos los clientes
    }
    
}


