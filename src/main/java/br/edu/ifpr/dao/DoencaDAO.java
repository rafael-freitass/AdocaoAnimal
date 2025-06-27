package br.edu.ifpr.dao;

import br.edu.ifpr.model.DoencaModel;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class DoencaDAO {

    private EntityManagerFactory emf = Persistence.createEntityManagerFactory("AdocaoAnimal");

    public void salvar(DoencaModel doenca) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.persist(doenca);
        em.getTransaction().commit();
        em.close();
    }
}
