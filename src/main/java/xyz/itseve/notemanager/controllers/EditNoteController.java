package xyz.itseve.notemanager.controllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import xyz.itseve.notemanager.Note;
import xyz.itseve.notemanager.NotePriority;

import java.net.URL;
import java.util.ResourceBundle;

public class EditNoteController implements Initializable {
    @FXML private ComboBox<String> notePriority;
    @FXML private TextField noteTitle;

    private Note self;
    public void setSelf(Note note) {
        self = note;

        noteTitle.setText(self.getTitle());
        notePriority.getSelectionModel().select(self.getPriority().name());
    }

    private NoteViewController parent;
    public void setParent(NoteViewController view) {
        parent = view;
    }

    private Stage parentStage;
    public void setParentStage(Stage stage) {
        parentStage = stage;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        notePriority.getItems().setAll("High", "Medium", "Low");
    }

    public void cancelCreation() {
        parentStage.close();
    }

    public void editNote() {
        String priority = notePriority.getSelectionModel().getSelectedItem();
        if (priority == null) {
            Alert alert = new Alert(
                Alert.AlertType.ERROR, "No priority selected! Please select a priority.", ButtonType.OK
            );
            alert.setHeaderText(null);
            alert.showAndWait();

            return;
        }

        String title = noteTitle.getText();
        if (title.isBlank()) {
            Alert alert = new Alert(
                Alert.AlertType.ERROR, "Title cannot be blank! Please set a note title.", ButtonType.OK
            );
            alert.setHeaderText(null);
            alert.showAndWait();

            return;
        }

        parent.notes.stream()
            .filter(note -> note.getId().equals(self.getId()))
            .findFirst()
            .ifPresent(note -> {
                note.setTitle(title);
                note.setPriority(NotePriority.valueOf(priority));

                note.createBody();

                parent.calculateNotes();
            });

        parentStage.close();
    }
}
