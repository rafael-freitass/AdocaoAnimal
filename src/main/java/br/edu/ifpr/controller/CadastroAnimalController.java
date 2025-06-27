package br.edu.ifpr.controller;

import br.edu.ifpr.model.AnimalModel;
import br.edu.ifpr.service.AnimalService;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;

public class CadastroAnimalController {
    private AnimalService animalService = new AnimalService();

    @FXML
    private Button btnLogout;

    @FXML
    private TextField nome;

    @FXML
    private TextField idade;

    @FXML
    private TextField raca;

    @FXML
    private ComboBox sexo;

    @FXML
    private ComboBox disponivel;

    @FXML
    private ComboBox castrado;

    @FXML
    private TextField descricao;

    @FXML
    private void handleLogout() {
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

    @FXML
    public void registrarAnimal() {
        try {
            AnimalModel animal = new AnimalModel();

            animal.setNome(nome.getText());
            int idadeInt = Integer.parseInt(idade.getText());
            animal.setIdade(idadeInt);

            animal.setRaca(raca.getText());

            String sexoSelecionado = (String) sexo.getValue();
            if (sexoSelecionado != null && sexoSelecionado.length() > 0)
                animal.setSexo(sexoSelecionado.charAt(0));

            String disponivelAdocao = (String) disponivel.getValue();
            animal.setDisponivelAdocao("Sim".equals(disponivelAdocao));

            String estaCastrado = (String) castrado.getValue();
            animal.setCastrado("Sim".equals(estaCastrado));

            animal.setDescricao(descricao.getText());

            animalService.cadastrarAnimal(animal);
            System.out.println("Animal cadastrado com sucesso!");

            abrirTela("TelaLogin.fxml");

        } catch (IllegalArgumentException e) {

            System.err.println("Erro de validação: " + e.getMessage());

        } catch (Exception e) {

            System.err.println("Erro ao cadastrar usuário: " + e.getMessage());
            e.printStackTrace();
        }
    }
}