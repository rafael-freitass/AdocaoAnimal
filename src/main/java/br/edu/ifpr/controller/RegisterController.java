package br.edu.ifpr.controller;

import br.edu.ifpr.model.UsuarioModel;
import br.edu.ifpr.model.enums.Role;
import br.edu.ifpr.service.UsuarioService;
import br.edu.ifpr.utils.AlertUtils;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class RegisterController {

    private UsuarioService usuarioService = new UsuarioService();

    @FXML private TextField nome;
    @FXML private TextField cpf;
    @FXML private TextField email;
    @FXML private TextField username;
    @FXML private TextField password;

    @FXML private void handleLoginScreen() {
        abrirTela("TelaLogin.fxml");
    }

    private void abrirTela(String caminhoFXML) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/br/edu/ifpr/" + caminhoFXML));
            Parent root = loader.load();
            Stage stage = (Stage) username.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML public void cadastrarUsuario() {
    try {
        UsuarioModel usuario = new UsuarioModel();
        usuario.setRole(Role.CLIENTE);
        usuario.setNome(nome.getText());
        usuario.setCpf(cpf.getText());
        usuario.setEmail(email.getText());
        usuario.setUsername(username.getText());
        usuario.setSenha(password.getText());

        usuarioService.cadastrar(usuario);
        AlertUtils.showSuccess("Usuário registrado com sucesso!");
        System.out.println("Usuário cadastrado com sucesso!");

        abrirTela("TelaLogin.fxml");

    } catch (IllegalArgumentException e) {
        AlertUtils.showError("Erro ao registrar usuário", e.getMessage());
        System.err.println("Erro de validação: " + e.getMessage());

    } catch (Exception e) {

        System.err.println("Erro ao cadastrar usuário: " + e.getMessage());
        e.printStackTrace();
    }
    }
}