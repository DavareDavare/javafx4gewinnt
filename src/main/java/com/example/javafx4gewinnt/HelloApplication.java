package com.example.javafx4gewinnt;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.fxml.Initializable;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;


// Falls Programm crashed
//
// --> In HelloController den Abstand bei dem Klassennamen hinzufügen bzw löschen
// --> Farbauswahl Pane beim Scenebuilder ausschneiden, speichern, ausführen, wieder einfügen und es sollte gehen
// --> "Target" Ordner löschen
//
//
public class HelloApplication extends Application implements Initializable {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1280, 720);
        String css = this.getClass().getResource("stylesheet.css").toExternalForm();
        scene.getStylesheets().add(css);
        stage.setResizable(false);
        stage.getIcons().add(new Image(getClass().getResourceAsStream("icon.png")));
        stage.setTitle("Mei HTL Lebn ois Spü!");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}