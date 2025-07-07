package br.edu.ifpr.controller;

import br.edu.ifpr.model.UsuarioModel;
import br.edu.ifpr.service.UsuarioService;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class LoginController {
    @FXML private TextField username;
    @FXML private TextField password;

    private final UsuarioService usuarioService = new UsuarioService();

    @FXML private void handleLogin() {
        String usernameInput = username.getText();
        String senhaInput = password.getText();

        UsuarioModel usuario = usuarioService.login(usernameInput, senhaInput);

        if (usuario == null) {
            System.out.println("Usuário ou senha incorretos.");
            return;
        }

        try {
            FXMLLoader loader;
            Parent root;

            switch (usuario.getRole()) {
                case ADMIN:
                case FUNCIONARIO:
                    loader = new FXMLLoader(getClass().getResource("/br/edu/ifpr/TelaSelecionarRegistro.fxml"));
                    root = loader.load();
                    break;

                case CLIENTE:
                default:
                    loader = new FXMLLoader(getClass().getResource("/br/edu/ifpr/TelaAnimais.fxml"));
                    root = loader.load();

                    TelaPrincipalController tp = loader.getController();
                    tp.setUsuarioLogado(usuario);
                    break;
            }

            Stage stage = (Stage) username.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML private void handleRegisterScreen() {
        abrirTela("TelaCadastro.fxml");
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
}
