package br.edu.ifpr.dao;

import br.edu.ifpr.model.VacinaModel;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class VacinaDAO {

    private EntityManagerFactory emf = Persistence.createEntityManagerFactory("AdocaoAnimal");

    public void salvar(VacinaModel vacina) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.persist(vacina);
        em.getTransaction().commit();
        em.close();
    }

    // Você pode adicionar métodos como listar(), buscarPorId(), deletar(), etc.
}
