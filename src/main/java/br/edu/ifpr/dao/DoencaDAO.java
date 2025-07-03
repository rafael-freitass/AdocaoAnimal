package br.edu.ifpr.dao;

import br.edu.ifpr.model.DoencaModel;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.util.List;

public class DoencaDAO {

    private EntityManagerFactory emf = Persistence.createEntityManagerFactory("AdocaoAnimal");

    public void salvar(DoencaModel doenca) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.persist(doenca);
        em.getTransaction().commit();
        em.close();
    }

    public List<DoencaModel> listarTodas() {
        EntityManager em = emf.createEntityManager();
        List<DoencaModel> doencas = em.createQuery("FROM DoencaModel", DoencaModel.class).getResultList();
        em.close();
        return doencas;
    }
}
