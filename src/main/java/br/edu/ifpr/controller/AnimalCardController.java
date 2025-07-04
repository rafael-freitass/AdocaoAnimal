package br.edu.ifpr.controller;

import br.edu.ifpr.model.AnimalModel;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.File;
import java.nio.file.Paths;

public class AnimalCardController {
    @FXML private ImageView imgAnimal;
    @FXML private Label lblNome;
    @FXML private Label lblRaca;
    @FXML private Button btnAdotar;

    public void setAnimal(AnimalModel animal) {
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

        btnAdotar.setOnAction(e -> {
            // lógica para adoção
        });
    }
}
