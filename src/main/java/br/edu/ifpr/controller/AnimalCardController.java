package br.edu.ifpr.controller;

import br.edu.ifpr.model.AnimalModel;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class AnimalCardController {
    @FXML private ImageView imgAnimal;
    @FXML private Label lblNome;
    @FXML private Label lblRaca;
    @FXML private Button btnAdotar;

    public void setAnimal(AnimalModel animal) {
        lblNome.setText(animal.getNome());
        lblRaca.setText(animal.getRaca());

        btnAdotar.setOnAction(e -> {
            // lógica para adoção
        });
    }
}
