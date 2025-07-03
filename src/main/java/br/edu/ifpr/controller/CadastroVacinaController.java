package br.edu.ifpr.controller;

import br.edu.ifpr.dao.VacinaDAO;
import br.edu.ifpr.model.VacinaModel;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.control.TextField;

import java.awt.*;
import java.io.IOException;

public class CadastroVacinaController {
    VacinaDAO vacinaDAO = new VacinaDAO();

    @FXML TextField nomeVacina;
    @FXML VBox VboxRoot;

    @FXML
    private void handleVoltar(){
        abrirTela("TelaSelecionarRegistro.fxml");
    }

    @FXML
    private void cadastroVacina(){
        VacinaModel vacina = new VacinaModel();
        vacina.setNome(nomeVacina.getText());

        vacinaDAO.salvar(vacina);
        System.out.println("Vacina cadastrada com sucesso!");
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
