package com.mycompany;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class MyEntityResource implements PanacheRepository<MyEntity> {
    // Aquí puedes añadir métodos personalizados para las consultas
}
