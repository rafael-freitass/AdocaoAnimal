package br.edu.ifpr.controller;

import br.edu.ifpr.dao.UsuarioDAO;
import br.edu.ifpr.model.UsuarioModel;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;

import java.io.IOException;

public class ControllerTelaGerenciarUsuarios {

    @FXML private TableView<UsuarioModel> tableUsuarios;
    @FXML private TableColumn<UsuarioModel, Integer> colId;
    @FXML private TableColumn<UsuarioModel, String> colNome;
    @FXML private TableColumn<UsuarioModel, String> colEmail;
    @FXML private TableColumn<UsuarioModel, String> colRole;

    @FXML private Button btnVoltar;
    @FXML private Button btnExcluir;
    @FXML private Button btnTornarAdmin;

    private ObservableList<UsuarioModel> listaUsuarios;
    private final UsuarioDAO usuarioDAO = new UsuarioDAO();

    @FXML private void initialize(){
        configurarTabela();
        carregarUsuarios();

        // Habilita botões apenas quando um usuário for selecionado
        tableUsuarios.getSelectionModel().selectedItemProperty().addListener((obs, oldVal, newVal) -> {
            boolean selecionado = newVal != null;
            btnExcluir.setDisable(!selecionado);
            btnTornarAdmin.setDisable(!selecionado);
        });
    }

    private void configurarTabela() {
        colId.setCellValueFactory(c -> new ReadOnlyObjectWrapper<>(c.getValue().getID()));
        colNome.setCellValueFactory(c -> new ReadOnlyObjectWrapper<>(c.getValue().getNome()));
        colEmail.setCellValueFactory(c -> new ReadOnlyObjectWrapper<>(c.getValue().getEmail()));
        colRole.setCellValueFactory(c -> new ReadOnlyObjectWrapper<>(c.getValue().getRole().name()));
    }

    private void carregarUsuarios() {
        listaUsuarios = FXCollections.observableArrayList(usuarioDAO.listarTodos());
        tableUsuarios.setItems(listaUsuarios);
    }

    @FXML private void handleExcluir() {
        UsuarioModel u = tableUsuarios.getSelectionModel().getSelectedItem();
        if (u != null) {
            usuarioDAO.excluir(u);
            listaUsuarios.remove(u);
        }
    }

    @FXML private void handleTornarAdmin() {
        UsuarioModel u = tableUsuarios.getSelectionModel().getSelectedItem();
        if (u != null && !u.getRole().name().equals("ADMIN")) {
            usuarioDAO.tornarAdmin(u);
            tableUsuarios.refresh();   // reflete a mudança no papel
        }
    }

    @FXML private void handleVoltar(){
        abrirTela("TelaSelecionarRegistro.fxml");
    }

    private void abrirTela(String caminhoFXML) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/br/edu/ifpr/" + caminhoFXML));
            Parent root = loader.load();
            Stage stage = (Stage) btnVoltar.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
