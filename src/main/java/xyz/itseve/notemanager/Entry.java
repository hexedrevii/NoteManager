package xyz.itseve.notemanager;

import com.fasterxml.jackson.databind.ObjectMapper;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;
import xyz.itseve.notemanager.controllers.NoteViewController;
import xyz.itseve.notemanager.models.Notes;
import xyz.itseve.notemanager.models.SaveNote;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Entry extends Application {
    public static void saveNotes(List<Note> notes) {
        try {
            List<SaveNote> saveNotes = new ArrayList<>();
            for (Note note : notes) {
                SaveNote saveNote = new SaveNote();
                saveNote.title = note.getTitle();
                saveNote.content = note.getContent();
                saveNote.priority = note.getPriority();

                saveNotes.add(saveNote);
            }

            Notes jsonNotes = new Notes();
            jsonNotes.data = saveNotes;

            ObjectMapper mapper = new ObjectMapper();
            String json = mapper.writeValueAsString(jsonNotes);

            Path config = Paths.get(System.getProperty("user.home"), ".config", "note_manager", "notes.json");
            Files.writeString(config, json);
        } catch (Exception e) {
            Alert alert = new Alert(
                Alert.AlertType.ERROR,
                "Could not write to config file: " + e.getMessage(),
                ButtonType.OK
            );
            alert.setHeaderText(null);

            alert.showAndWait();
        }
    }

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Entry.class.getResource("note-view.fxml"));

        Scene scene = new Scene(fxmlLoader.load(), 800, 600);
        scene.getStylesheets().add(
            Objects.requireNonNull(
                Entry.class.getResource("styles/note-view.css")
            ).toExternalForm()
        );

        NoteViewController controller = fxmlLoader.getController();
        controller.setMainStage(stage);

        stage.setTitle("Note Manager");
        stage.setScene(scene);

        String home = System.getProperty("user.home");
        Path combined = Paths.get(home, ".config", "note_manager");

        if (!Files.exists(combined)) {
            System.out.println("Configuration directory does not exist. Creating.");
            Files.createDirectories(combined);
        }

        Path config = Paths.get(home, ".config", "note_manager", "notes.json");
        ObjectMapper mapper = new ObjectMapper();
        if (!Files.exists(config)) {
            Notes notes = new Notes();
            notes.data = new ArrayList<>();

            String json = mapper.writeValueAsString(notes);

            Files.createFile(config);
            Files.writeString(config, json);
        } else {
            String json = Files.readString(config);

            Notes notes = mapper.readValue(json, Notes.class);
            for (SaveNote note : notes.data) {
                controller.notes.add(
                    new Note(note.title, note.content, note.priority, controller)
                );
            }

            controller.calculateNotes();
        }

        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}