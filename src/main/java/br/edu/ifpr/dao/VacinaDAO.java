package br.edu.ifpr.dao;

import br.edu.ifpr.model.VacinaModel;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.util.List;

public class VacinaDAO {

    private EntityManagerFactory emf = Persistence.createEntityManagerFactory("AdocaoAnimal");

    public void salvar(VacinaModel vacina) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.persist(vacina);
        em.getTransaction().commit();
        em.close();
    }

    public List<VacinaModel> listarTodas() {
        EntityManager em = emf.createEntityManager();
        List<VacinaModel> vacinas = em.createQuery("FROM VacinaModel", VacinaModel.class).getResultList();
        em.close();
        return vacinas;
    }

}
