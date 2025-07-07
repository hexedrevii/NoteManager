package xyz.itseve.notemanager;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class Entry extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Entry.class.getResource("note-view.fxml"));

        Scene scene = new Scene(fxmlLoader.load(), 800, 600);
        scene.getStylesheets().add(
            Objects.requireNonNull(
                Entry.class.getResource("styles/note-view.css")
            ).toExternalForm()
        );

        stage.setTitle("Note Manager");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}