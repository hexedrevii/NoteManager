package xyz.itseve.notemanager;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;

import java.net.URL;
import java.util.ResourceBundle;

public class NoteViewController implements Initializable {
    @FXML public FlowPane cardHolder;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Note def = new Note("I WILL NOT FALTER", "I WILL NOT FALTER");
        cardHolder.getChildren().add(def.getBody());

        Note def1 = new Note("I WILL NOT FALTER 1", "I WILL NOT FALTER");
        cardHolder.getChildren().add(def1.getBody());

        Note def2 = new Note("I WILL NOT FALTER 2", "I WILL NOT FALTER");
        cardHolder.getChildren().add(def2.getBody());

        Note def3 = new Note("I WILL NOT FALTER 3", "I WILL NOT FALTER");
        cardHolder.getChildren().add(def3.getBody());

        Note def4 = new Note("I WILL NOT FALTER 4", "I WILL NOT FALTER");
        cardHolder.getChildren().add(def4.getBody());

        Note def5 = new Note("I WILL NOT FALTER 5", "I WILL NOT FALTER");
        cardHolder.getChildren().add(def5.getBody());

    }
}