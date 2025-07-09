package br.edu.ifpr.controller;

import br.edu.ifpr.dao.AnimalDAO;
import br.edu.ifpr.model.AnimalModel;
import br.edu.ifpr.model.UsuarioModel;
import br.edu.ifpr.utils.AlertUtils;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.List;

public class TelaPrincipalController {
    @FXML private Button btnLogout;
    @FXML private FlowPane paneAnimais;

    private UsuarioModel usuarioLogado;

    public void setUsuarioLogado(UsuarioModel usuario) {
        this.usuarioLogado = usuario;
        carregarAnimais();
    }

    private void carregarAnimais() {
        paneAnimais.getChildren().clear();

        List<AnimalModel> animaisDisponiveis = AnimalDAO.buscarDisponiveis();
        for (AnimalModel animal : animaisDisponiveis) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/br/edu/ifpr/AnimalCard.fxml"));
                Parent card = loader.load();

                AnimalCardController cardCtrl = loader.getController();
                cardCtrl.setAnimal(animal);
                cardCtrl.setUsuarioLogado(usuarioLogado);

                paneAnimais.getChildren().add(card);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        paneAnimais.setPrefWrapLength(800);
    }

    @FXML private void handleLogout() {
        abrirTela("TelaLogin.fxml");
    }

    private void abrirTela(String caminhoFXML) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/br/edu/ifpr/" + caminhoFXML));
            Parent root = loader.load();
            Stage stage = (Stage) btnLogout.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
