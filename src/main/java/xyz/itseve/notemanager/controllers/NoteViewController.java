package xyz.itseve.notemanager.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.layout.FlowPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import xyz.itseve.notemanager.Entry;
import xyz.itseve.notemanager.Note;
import xyz.itseve.notemanager.NotePriority;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.ResourceBundle;

public class NoteViewController implements Initializable {
    @FXML private FlowPane cardHolder;
    @FXML private TextField searchFilter;

    private Stage mainStage;
    public void setMainStage(Stage stage) {
        mainStage = stage;
    }

    private final List<Note> notes = new ArrayList<Note>();

    private void calculateNotes(String txt) {
        cardHolder.getChildren().clear();

        List<Note> filtered = notes.stream()
                .filter(note -> note.getTitle().toLowerCase().contains(txt.toLowerCase()))
                .toList();

        for (Note n : filtered) cardHolder.getChildren().add(n.getBody());
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        searchFilter.textProperty().addListener((obs, oldText, newText) -> {
            calculateNotes(newText);
        });
    }

    @FXML
    private void openCreateDialogue(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(Objects.requireNonNull(Entry.class.getResource("add-note.fxml")));
        Parent root = loader.load();
        Stage addNote = new Stage();

        AddNoteController controller = loader.getController();
        controller.setParentStage(addNote);

        addNote.setTitle("Add note");
        addNote.initModality(Modality.APPLICATION_MODAL);
        addNote.setScene(new Scene(root));
        addNote.initOwner(mainStage);
        addNote.setResizable(false);

        addNote.showAndWait();

        Note toAdd = controller.getNote();
        if (toAdd == null) return;

        notes.addFirst(toAdd);
        calculateNotes(searchFilter.getText().trim().toLowerCase());
    }

    @FXML
    private void closeWindow(ActionEvent actionEvent) {
        mainStage.close();
    }
}