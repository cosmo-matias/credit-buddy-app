package br.com.ducosmo.creditbuddy;

import br.com.ducosmo.creditbuddy.service.DatabaseService;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class MainApplication extends Application {

    @Override
    public void start(Stage stage) throws IOException {
        // GARANTE QUE O BANCO DE DADOS E AS TABELAS ESTEJAM PRONTOS
        DatabaseService.initializeDatabase();

        FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("main-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 800, 600);
        stage.setTitle("Credit Buddy | by Ducosmo");
        stage.setScene(scene);
        stage.show();
    }

    // O MÉTODO MAIN FOI REMOVIDO DAQUI, O QUE ESTÁ CORRETO.
    // O MÉTODO START DUPLICADO FOI REMOVIDO.
}