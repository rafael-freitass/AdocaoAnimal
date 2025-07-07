package br.edu.ifpr.controller;

import br.edu.ifpr.model.AnimalModel;
import br.edu.ifpr.model.UsuarioModel;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;

public class AnimalCardController {
    @FXML private ImageView imgAnimal;
    @FXML private Label lblNome;
    @FXML private Label lblRaca;
    @FXML private Button btnAdotar;
    private AnimalModel animal;
    private UsuarioModel usuarioLogado;

    public void setAnimal(AnimalModel animal) {
        this.animal = animal;
        lblNome.setText(animal.getNome());
        lblRaca.setText(animal.getRaca());

        String caminhoImagem = animal.getFoto();

        if (caminhoImagem != null && !caminhoImagem.isBlank()) {
            File arquivoImagem = Paths.get(caminhoImagem).toAbsolutePath().toFile();

            System.out.println("Procurando imagem em: " + arquivoImagem.getAbsolutePath());
            if (arquivoImagem.exists()) {
                imgAnimal.setImage(new Image(arquivoImagem.toURI().toString()));
            } else {
                System.out.println("Imagem não encontrada: " + arquivoImagem.getAbsolutePath());
            }
        } else {
            System.out.println("Animal sem foto cadastrada");
        }
    }
    public void setUsuarioLogado(UsuarioModel usuario) {
        this.usuarioLogado = usuario;
    }

    @FXML
    private void handleTelaAdotar() {
        abrirTela("AnimalInfo.fxml");
    }

    private void abrirTela(String caminhoFXML) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/br/edu/ifpr/" + caminhoFXML));
            Parent root = loader.load();

            AnimalInfoController infoController = loader.getController();
            infoController.setAnimalAdocao(animal);
            infoController.setUsuarioLogado(usuarioLogado);

            Stage stage = (Stage) btnAdotar.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
