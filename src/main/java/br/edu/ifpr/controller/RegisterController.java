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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

public class RegisterController {
    private final Logger log = LoggerFactory.getLogger(RegisterController.class);
    private UsuarioService usuarioService = new UsuarioService();

    @FXML
    private TextField nome;
    @FXML
    private TextField cpf;
    @FXML
    private TextField email;
    @FXML
    private TextField username;
    @FXML
    private TextField password;

    @FXML
    private void handleLoginScreen() {
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
            log.error("Erro ao abrir a tela: {}", e.getMessage(), e);
        }
    }

    @FXML
    public void cadastrarUsuario() {
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
            log.info("Usuário cadastrado com sucesso!");

            abrirTela("TelaLogin.fxml");

        } catch (IllegalArgumentException e) {
            AlertUtils.showError("Erro ao registrar usuário", e.getMessage());
            log.error("Erro de validação: {}", e.getMessage(), e);
        } catch (Exception e) {
            log.error("Erro ao cadastrar usuário: {}", e.getMessage(), e);
        }
    }
}