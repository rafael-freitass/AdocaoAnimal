package br.edu.ifpr.utils;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;

public class AlertUtils {

    public static void showInfo(String titulo, String mensagem) {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensagem);
        alert.showAndWait();
    }

    public static void showError(String titulo, String mensagem) {
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle(titulo);
        alert.setHeaderText("Ocorreu um erro!");
        alert.setContentText(mensagem);
        alert.showAndWait();
    }

    public static boolean showConfirmation(String titulo, String mensagem) {
        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensagem);

        return alert.showAndWait().filter(response -> response == ButtonType.OK).isPresent();
    }

    public static void showSuccess(String mensagem) {
        showInfo("Sucesso!", mensagem);
    }

    public static void showAdoptionSuccess(String nomeAnimal) {
        showInfo("Adoção Concluída 🎉", "Parabéns pela adoção do(a) " + nomeAnimal + "!\nObrigado por fazer a diferença.");
    }
}
