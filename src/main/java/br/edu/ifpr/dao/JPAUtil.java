package br.edu.ifpr.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class JPAUtil <T> {

    private static final EntityManagerFactory emf =
            Persistence.createEntityManagerFactory("AdocaoAnimal");

    public static EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void save(T entity) {
        EntityManager em = getEntityManager();
        em.getTransaction().begin();
        em.persist(entity);
        em.getTransaction().commit();
        em.close();
    }

    public static void close() {
        emf.close();
    }
}
