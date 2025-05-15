package br.edu.ifpr.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.control.TextField;

import java.io.IOException;

public class LoginController {
    @FXML
    private TextField username;

    @FXML
    private TextField password;

    @FXML
    public void realizarLogin(){
        if (username.getText().equals("admin") && password.getText().equals("admin")){
            try {
                // Carrega a nova tela FXML (TelaPrincipal.fxml)
                FXMLLoader loader = new FXMLLoader(LoginController.class.getResource("/br/edu/ifpr/TelaRegistrarAnimais.fxml"));
                Parent root = loader.load();

                // Obtém o stage atual a partir de qualquer elemento da tela (ex: username)
                Stage stage = (Stage) username.getScene().getWindow();

                // Substitui o conteúdo atual pela nova tela
                stage.setScene(new Scene(root, 900, 600));
                stage.setTitle("Registrar Animais");
                stage.show();

            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("Login inválido");
        }
    }
}
