package com.mycompany.repositorio;

import com.mycompany.model.Vendedores;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import java.util.List;
import javax.enterprise.context.RequestScoped;
import javax.persistence.Query;
import javax.transaction.Transactional;

@RequestScoped
public class VendedoresRepositorio implements PanacheRepository<Vendedores> {

    @Transactional
    public void create(Vendedores vendedor) {
        this.persist(vendedor);
    }

    public List<Vendedores> findVendedores() {
        try {
            Query w = getEntityManager().createQuery("SELECT v FROM Vendedores v order by v.idVendedores asc");
            return w.getResultList();
        } catch (Exception e) {
            System.out.println("e->" + e);
        }
        return null;
    }
public Vendedores findVendedores_id(Long id){
return find("idVendedores",id).firstResult();
}
@Transactional
public void updateVendedores(Vendedores vendedor){
this.getEntityManager().merge(vendedor);
}

}
