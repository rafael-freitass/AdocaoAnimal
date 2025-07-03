package br.edu.ifpr.dao;

import br.edu.ifpr.model.AnimalModel;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import java.util.List;

public class AnimalDAO {

    public void salvar(AnimalModel animal) {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(animal);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            throw e;
        } finally {
            em.close();
        }
    }

    public void atualizar(AnimalModel animal) {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            em.getTransaction().begin();
            em.merge(animal);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            throw e;
        } finally {
            em.close();
        }
    }

    public void excluir(int id) {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            em.getTransaction().begin();
            AnimalModel animal = em.find(AnimalModel.class, id);
            if (animal != null) {
                em.remove(animal);
            }
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            throw e;
        } finally {
            em.close();
        }
    }

    public AnimalModel buscarPorId(int id) {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            return em.find(AnimalModel.class, id);
        } finally {
            em.close();
        }
    }

    public List<AnimalModel> buscarTodos() {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            TypedQuery<AnimalModel> query = em.createQuery(
                    "SELECT a FROM AnimalModel a ORDER BY a.nome",
                    AnimalModel.class);
            return query.getResultList();
        } finally {
            em.close();
        }
    }

    public static List<AnimalModel> buscarDisponiveis() {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            return em.createQuery("SELECT a FROM AnimalModel a WHERE a.disponivelAdocao = true", AnimalModel.class)
                    .getResultList();
        } finally {
            em.close();
        }
    }


}