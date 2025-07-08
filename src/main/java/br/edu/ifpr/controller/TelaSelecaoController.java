package br.edu.ifpr.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.awt.*;
import java.io.IOException;


public class TelaSelecaoController {

    @FXML VBox rootVbox;

    @FXML private void chamarTelaRegistroAnimal(){
        abrirTela("TelaRegistrarAnimais.fxml");
    }

    @FXML private void chamarTelaRegistroVacina(){
        abrirTela("TelaVacina.fxml");
    }

    @FXML private void chamarTelaRegistroDoenca(){
        abrirTela("TelaDoenca.fxml");
    }

    @FXML private void chamarTelaGerenciarUsuario(){
        abrirTela("TelaGerenciarUsuarios.fxml");
    }

    @FXML private void handleLogout(){
        abrirTela("TelaLogin.fxml");
    }

    private void abrirTela(String caminhoFXML) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/br/edu/ifpr/" + caminhoFXML));
            Parent root = loader.load();
            Stage stage = (Stage) rootVbox.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}