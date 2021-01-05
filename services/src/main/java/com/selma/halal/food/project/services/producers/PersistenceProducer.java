package com.selma.halal.food.project.services.producers;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.Produces;

public class PersistenceProducer {

    @PersistenceContext(unitName = "add-place-metadata-jpa")
    private EntityManager entityManager;

    @Produces
    @ApplicationScoped
    public EntityManager entityManager(){
        return entityManager;
    }
}
