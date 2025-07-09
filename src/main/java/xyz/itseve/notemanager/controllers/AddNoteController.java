package xyz.itseve.notemanager.controllers;

import javafx.event.ActionEvent;
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

public class AddNoteController implements Initializable {
    @FXML private TextField noteTitle;
    @FXML private ComboBox<String> notePriority;

    private Stage parentStage;
    public void setParentStage(Stage stage) {
        parentStage = stage;
    }

    private Note note = null;
    public final Note getNote() {
        return note;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        notePriority.getItems().setAll("High", "Medium", "Low");
    }

    @FXML
    private void createNote(ActionEvent actionEvent) {
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

        note = new Note(title, "", NotePriority.valueOf(priority));
        parentStage.close();
    }

    @FXML
    private void cancelCreation(ActionEvent actionEvent) {
        parentStage.close();
    }
}
