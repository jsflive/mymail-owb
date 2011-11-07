package at.irian.dao;

import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Default;
import javax.enterprise.inject.Disposes;
import javax.enterprise.inject.Produces;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

public class EntityManagerProducer {
    @PersistenceContext(unitName = "mymail")
    private EntityManager entityManager;

    @Produces @Default @RequestScoped
    public EntityManager createEntityManager() {
        return this.entityManager;
    }

    public void dispose(@Disposes @Default EntityManager entityManager) {
        if (entityManager.isOpen()) {
            entityManager.close();
        }
    }

}