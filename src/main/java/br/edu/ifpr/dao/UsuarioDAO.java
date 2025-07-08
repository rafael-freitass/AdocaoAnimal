package br.edu.ifpr.dao;

import br.edu.ifpr.model.UsuarioModel;
import br.edu.ifpr.model.enums.Role;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.NoResultException;
import jakarta.persistence.TypedQuery;

import java.util.List;
import java.util.function.Consumer;

public class UsuarioDAO extends JPAUtil<UsuarioModel> {

    public List<UsuarioModel> listarTodos() {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            return em.createQuery("SELECT u FROM UsuarioModel u", UsuarioModel.class)
                    .getResultList();
        } finally {
            em.close();
        }
    }

    public void excluir(UsuarioModel usuario) {
        executarTransacao(em -> {
            UsuarioModel ref = em.merge(usuario);
            em.remove(ref);
        });
    }

    private void executarTransacao(Consumer<EntityManager> bloco) {
        EntityManager em = JPAUtil.getEntityManager();
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            bloco.accept(em);
            tx.commit();
        } catch (Exception e) {
            if (tx.isActive()) tx.rollback();
            throw e;
        } finally {
            em.close();
        }
    }

    public void tornarAdmin(UsuarioModel usuario) {
        executarTransacao(em -> {
            UsuarioModel ref = em.merge(usuario);
            ref.setRole(Role.ADMIN);         // muda o papel
        });
    }

    public UsuarioModel buscarPorUsernameESenha(String username, String senha) {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            TypedQuery<UsuarioModel> query = em.createQuery(
                    "SELECT u FROM UsuarioModel u WHERE u.username = :username AND u.senha = :senha",
                    UsuarioModel.class);
            query.setParameter("username", username);
            query.setParameter("senha", senha);
            return query.getSingleResult();
        } catch (NoResultException e) {
            return null;
        } finally {
            em.close();
        }
    }
}
