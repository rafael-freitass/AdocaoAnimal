package br.edu.ifpr.dao;

import br.edu.ifpr.model.UsuarioModel;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.TypedQuery;

public class UsuarioDAO extends JPAUtil<UsuarioModel> {

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
