package com.mycompany.repositorio;

import com.mycompany.model.Clientes;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import java.util.List;
import javax.enterprise.context.RequestScoped;
import javax.persistence.Query;
import javax.transaction.Transactional;

@RequestScoped
public class ClienteRepositorio implements PanacheRepository<Clientes> {

    @Transactional
    public void create(Clientes cliente) {
        this.persist(cliente);
    }

    public List<Clientes> findClientes() {
        try {
            Query q = getEntityManager().createQuery("SELECT c FROM Clientes c order by c.idCliente asc");
            return q.getResultList();
        } catch (Exception e) {
            System.out.println("e->" + e);
            return null;
        }
    }
    
    public Clientes findCliente_Id(Long id) {
        return find("idCliente", id).firstResult();
    }

    @Transactional
    public void updateCliente(Clientes cliente) {
        this.getEntityManager().merge(cliente);
    }
    
    @Transactional
    public void deleteCliente(Long id) {
        Clientes cliente = findCliente_Id(id);
        if (cliente != null) {
            this.delete(cliente);
        }
    }
}