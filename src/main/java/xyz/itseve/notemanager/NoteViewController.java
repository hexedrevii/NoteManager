package xyz.itseve.notemanager;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class NoteViewController {
    @FXML
    private Label welcomeText;

    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to JavaFX Application!");
    }
}