package br.com.ducosmo.creditbuddy;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public class MainController {

    @FXML
    private void abrirCadastroPessoas() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("pessoas-view.fxml"));
            Scene scene = new Scene(fxmlLoader.load());

            Stage stage = new Stage();
            stage.setTitle("Cadastro de Pessoas");
            stage.setScene(scene);

            // Modality.APPLICATION_MODAL impede que o usuário clique na janela principal
            // enquanto a janela de cadastro estiver aberta.
            stage.initModality(Modality.APPLICATION_MODAL);

            stage.showAndWait();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void abrirCadastroCartoes() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("cartoes-view.fxml"));
            Scene scene = new Scene(fxmlLoader.load());
            Stage stage = new Stage();
            stage.setTitle("Cadastro de Cartões");
            stage.setScene(scene);
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.showAndWait();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}