package br.edu.ifpr.service;

import br.edu.ifpr.dao.UsuarioDAO;
import br.edu.ifpr.model.UsuarioModel;

public class UsuarioService {

    private UsuarioDAO usuarioDAO = new UsuarioDAO();

    public UsuarioModel login(String username, String senha) {
        return usuarioDAO.buscarPorUsernameESenha(username, senha);
    }

    public void cadastrar(UsuarioModel usuario) throws IllegalArgumentException {

        if (usuario.getNome() == null || usuario.getNome().trim().isEmpty()) {
            throw new IllegalArgumentException("O nome é obrigatório.");
        }

        if (usuario.getCpf() == null || !usuario.getCpf().matches("\\d{11}")) {
            throw new IllegalArgumentException("CPF inválido. Deve conter 11 dígitos numéricos.");
        }

        if (usuario.getEmail() == null || !usuario.getEmail().matches("^[\\w\\.-]+@[\\w\\.-]+\\.\\w+$")) {
            throw new IllegalArgumentException("Email inválido.");
        }

        if (usuario.getUsername() == null || usuario.getUsername().trim().isEmpty()) {
            throw new IllegalArgumentException("O nome de usuário é obrigatório.");
        }

        if (usuario.getSenha() == null || usuario.getSenha().length() < 6) {
            throw new IllegalArgumentException("A senha deve conter pelo menos 6 caracteres.");
        }

        usuarioDAO.save(usuario);

    }
}
