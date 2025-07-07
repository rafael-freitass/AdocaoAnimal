package br.edu.ifpr.dao;

import br.edu.ifpr.model.AdocaoModel;
import br.edu.ifpr.model.AnimalModel;
import br.edu.ifpr.model.UsuarioModel;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;

import java.util.Date;
import java.util.List;


public class AdocaoDAO {

    public static void realizarAdocao(AnimalModel animal, UsuarioModel usuario) {
        EntityManager em = JPAUtil.getEntityManager();

        try {
            em.getTransaction().begin();

            animal   = em.merge(animal);
            usuario  = em.merge(usuario);

            animal.setDisponivelAdocao(false);
            em.merge(animal);

            AdocaoModel adocao = new AdocaoModel();
            adocao.setAnimal(animal);
            adocao.setCliente(usuario);
            adocao.setDataAdocao(new Date());
            em.persist(adocao);

            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            throw e;
        } finally {
            em.close();
        }
    }
}