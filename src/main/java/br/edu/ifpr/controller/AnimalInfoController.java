package br.edu.ifpr.controller;

import br.edu.ifpr.model.AnimalModel;
import br.edu.ifpr.dao.AdocaoDAO;
import br.edu.ifpr.model.DoencaModel;
import br.edu.ifpr.model.UsuarioModel;
import br.edu.ifpr.model.VacinaModel;
import br.edu.ifpr.utils.AlertUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;

public class AnimalInfoController {
    @FXML private Label lblNome;
    @FXML private Label lblRaca;
    @FXML private Label lbldescricao;
    @FXML private ImageView imgAnimal;
    @FXML private Button btnAdotar;
    @FXML private ListView<DoencaModel> listDoencas;
    @FXML private ListView<VacinaModel> listVacinas;

    private AnimalModel animal;
    private UsuarioModel usuarioLogado;
    private final AdocaoDAO adocaoDAO = new AdocaoDAO();

    public void setAnimalAdocao(AnimalModel animal) {
        this.animal = animal;

        lblNome.setText(animal.getNome());
        lblRaca.setText(animal.getRaca());

        if (animal.getDescricao() == null || animal.getDescricao().isBlank()) {
            lbldescricao.setText("Sem descrição cadastrada.");
        } else {
            lbldescricao.setText(animal.getDescricao());
        }

        String caminhoImagem = animal.getFoto();
        if (caminhoImagem != null && !caminhoImagem.isBlank()) {
            File arquivoImagem = Paths.get(caminhoImagem).toAbsolutePath().toFile();

            System.out.println("Procurando imagem em: " + arquivoImagem.getAbsolutePath());
            if (arquivoImagem.exists()) {
                imgAnimal.setImage(new Image(arquivoImagem.toURI().toString()));
            } else {
                usarImagemPadrao();
                System.out.println("Imagem não encontrada: " + arquivoImagem.getAbsolutePath());
            }
        } else {
            usarImagemPadrao();
            System.out.println("Animal sem foto cadastrada");
        }
        listDoencas.getItems().setAll(animal.getDoenca());
        listVacinas.getItems().setAll(animal.getVacina());

        listDoencas.setPlaceholder(new Label("Nenhuma doença registrada."));
        listVacinas.setPlaceholder(new Label("Nenhuma vacina registrada."));
    }

    private void usarImagemPadrao() {
        String caminhoImagem = "imagens/sem_foto.png";
        File imgPadrao = Paths.get(caminhoImagem).toAbsolutePath().toFile();
        imgAnimal.setImage(new Image(imgPadrao.toURI().toString()));
    }

    public void setUsuarioLogado(UsuarioModel usuario) {
        this.usuarioLogado = usuario;
    }

    @FXML public void handleAdocao(ActionEvent event){
        try {
            adocaoDAO.realizarAdocao(animal, usuarioLogado);
            AlertUtils.showAdoptionSuccess(animal.getNome());
            abrirTela("TelaAnimais.fxml");
        } catch (Exception e) {
            System.out.println("Falha ao registrar adoção:\n" + e.getMessage());
        }
    }

    @FXML private void handleLogout() {
        abrirTela("TelaAnimais.fxml");
    }

    private void abrirTela(String caminhoFXML) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/br/edu/ifpr/" + caminhoFXML));
            Parent root = loader.load();

            TelaPrincipalController controller = loader.getController();
            controller.setUsuarioLogado(usuarioLogado);

            Stage stage = (Stage) btnAdotar.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
