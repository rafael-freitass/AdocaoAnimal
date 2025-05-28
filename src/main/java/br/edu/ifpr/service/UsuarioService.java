package br.edu.ifpr.service;

import br.edu.ifpr.dao.UsuarioDAO;
import br.edu.ifpr.model.UsuarioModel;

public class UsuarioService {
    public UsuarioModel login(String username, String senha) {
        UsuarioDAO usuarioDAO = new UsuarioDAO();
        return usuarioDAO.buscarPorUsernameESenha(username, senha);
    }
}
