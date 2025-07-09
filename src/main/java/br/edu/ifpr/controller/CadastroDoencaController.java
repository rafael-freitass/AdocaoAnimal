package br.edu.ifpr.controller;

import br.edu.ifpr.dao.DoencaDAO;
import br.edu.ifpr.model.DoencaModel;
import br.edu.ifpr.utils.AlertUtils;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import javax.swing.*;
import java.io.IOException;

public class CadastroDoencaController {
    private DoencaDAO doencaDAO = new DoencaDAO();

    @FXML TextField nomeDoenca;
    @FXML VBox VboxRoot;

    @FXML
    private void handleVoltar(){
        abrirTela("TelaSelecionarRegistro.fxml");
    }

    @FXML
    private void cadastrarDoenca(){
        DoencaModel doenca = new DoencaModel();
        doenca.setNome(nomeDoenca.getText());

        doencaDAO.salvar(doenca);
        AlertUtils.showSuccess("Doença cadastrada com sucesso!");
        System.out.println("Doença cadastrada com sucesso!");
    }

    private void abrirTela(String caminhoFXML) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/br/edu/ifpr/" + caminhoFXML));
            Parent root = loader.load();
            Stage stage = (Stage) VboxRoot.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
