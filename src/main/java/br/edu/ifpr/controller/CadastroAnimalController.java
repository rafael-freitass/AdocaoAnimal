package br.edu.ifpr.controller;

import br.edu.ifpr.dao.DoencaDAO;
import br.edu.ifpr.dao.VacinaDAO;
import br.edu.ifpr.model.AnimalModel;
import br.edu.ifpr.model.DoencaModel;
import br.edu.ifpr.model.VacinaModel;
import br.edu.ifpr.service.AnimalService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class CadastroAnimalController {
    @FXML private Button btnLogout;
    @FXML private TextField nome;
    @FXML private TextField idade;
    @FXML private TextField raca;
    @FXML private ComboBox sexo;
    @FXML private ComboBox disponivel;
    @FXML private ComboBox castrado;
    @FXML private TextArea descricao;
    @FXML private Button btnCarregar;
    @FXML private ImageView imgPreview;
    @FXML private ListView<DoencaModel> listaDoencas;
    @FXML private ListView<VacinaModel> listaVacinas;
    private String caminhoImagemRelativo = null;

    private AnimalService animalService = new AnimalService();
    private DoencaDAO doencaDAO = new DoencaDAO();
    private VacinaDAO vacinaDAO = new VacinaDAO();

    @FXML private void handleLogout() {
        abrirTela("TelaSelecionarRegistro.fxml");
    }


    @FXML private void carregarImagem(){
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Selecionar imagem do animal");

        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Imagens", "*.png", "*.jpg", "*.jpeg")
        );

        File arquivoSelecionado = fileChooser.showOpenDialog(btnCarregar.getScene().getWindow());

        if (arquivoSelecionado != null){
            try {
                File pastaDestino = new File("imagens/animais");
                if (!pastaDestino.exists()) {
                    pastaDestino.mkdirs();
                }

                String nomeArquivo = arquivoSelecionado.getName();
                File destino = new File(pastaDestino, nomeArquivo);

                java.nio.file.Files.copy(
                        arquivoSelecionado.toPath(),
                        destino.toPath(),
                        java.nio.file.StandardCopyOption.REPLACE_EXISTING
                );

                caminhoImagemRelativo = "imagens/animais/" + nomeArquivo;

                imgPreview.setImage(new Image(destino.toURI().toString()));

                System.out.println("Imagem carregada e copiada para: " + caminhoImagemRelativo);
            }
            catch (IOException e) {
                e.printStackTrace();
            }
        }
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


    @FXML public void registrarAnimal() {
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

            if (caminhoImagemRelativo != null) {
                animal.setFoto(caminhoImagemRelativo);
            }

            animal.setVacina(new ArrayList<>(listaVacinas.getSelectionModel().getSelectedItems()));
            animal.setDoenca(new ArrayList<>(listaDoencas.getSelectionModel().getSelectedItems()));


            animalService.cadastrarAnimal(animal);

            System.out.println("Animal cadastrado com sucesso!");

        } catch (IllegalArgumentException e) {

            System.err.println("Erro de validação: " + e.getMessage());

        } catch (Exception e) {

            System.err.println("Erro ao cadastrar usuário: " + e.getMessage());
            e.printStackTrace();
        }
    }

    @FXML private void initialize() {
        listaDoencas.setItems(FXCollections.observableArrayList(doencaDAO.listarTodas()));
        listaVacinas.setItems(FXCollections.observableArrayList(vacinaDAO.listarTodas()));

        listaDoencas.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        listaVacinas.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

        ObservableList<DoencaModel> doencasSelecionadas = listaDoencas.getSelectionModel().getSelectedItems();
        ObservableList<VacinaModel> vacinasSelecionadas = listaVacinas.getSelectionModel().getSelectedItems();
    }


}