package xyz.itseve.notemanager;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.FlowPane;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

public class NoteViewController implements Initializable {
    @FXML public FlowPane cardHolder;
    @FXML public TextField searchFilter;

    private final List<Note> notes = new ArrayList<Note>();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        searchFilter.textProperty().addListener((obs, oldText, newText) -> {
            cardHolder.getChildren().clear();

            List<Note> filtered = notes.stream()
                .filter(note -> note.getTitle().toLowerCase().contains(newText.toLowerCase()))
                .toList();

            for (Note n : filtered) cardHolder.getChildren().add(n.getBody());
        });

        for (int i = 0; i < 6; i++) {
            addNote("I WILL NOT FALTER " + i, "I WILL NOT FALTER");
        }
    }

    private void addNote(String name, String content) {
        Note note = new Note(name, content);
        cardHolder.getChildren().add(note.getBody());

        notes.add(note);
    }
}