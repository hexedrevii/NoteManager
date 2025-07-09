package xyz.itseve.notemanager;

import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import xyz.itseve.notemanager.controllers.NoteViewController;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

public class Note {
    private String title;
    public final String getTitle() {
        return title;
    }

    private String content;
    private NotePriority priority;

    private final LocalDateTime added;

    private final UUID id;
    public final UUID getId() {
        return id;
    }

    private final NoteViewController noteController;

    private VBox body;
    public final VBox getBody() {
        return body;
    }

    public Note(String title, String content, NotePriority priority, NoteViewController controller) {
        this.title = title;
        this.content = content;
        this.priority = priority;

        added = LocalDateTime.now();
        id = UUID.randomUUID();

        noteController = controller;
        createBody();
    }

    private void createBody() {
        body = new VBox();
        body.setMinWidth(250);
        body.setMinHeight(300);

        body.getStyleClass().add("note-card");
        body.getStylesheets().add(
            Objects.requireNonNull(Entry.class.getResource("styles/note.css")).toExternalForm()
        );

        Label titleLabel = new Label(title);
        titleLabel.setId("ttl");

        Label priorityLabel = new Label(priority.name().equals("Medium") ? "Med" : priority.name());
        priorityLabel.getStyleClass().add("note-priority");
        priorityLabel.setId(priority.name().toLowerCase());

        BorderPane titleBox = new BorderPane();
        titleBox.setCenter(titleLabel);
        titleBox.setRight(priorityLabel);

        TextArea contentArea = new TextArea(content);
        contentArea.setId("cnt");
        contentArea.setWrapText(true);
        contentArea.setPrefColumnCount(4);
        VBox.setVgrow(contentArea, Priority.ALWAYS);

        BorderPane footer = new BorderPane();
        Label addedLabel = new Label("Added on " + added.format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss")));
        addedLabel.setId("adl");

        ImageView trashIcon = new ImageView(
            new Image(
                Objects.requireNonNull(Entry.class.getResourceAsStream("icons/trash.png"))
            )
        );
        trashIcon.setFitHeight(20);
        trashIcon.setFitWidth(20);
        trashIcon.setPreserveRatio(true);

        ImageView editIcon = new ImageView(
            new Image(
                Objects.requireNonNull(Entry.class.getResourceAsStream("icons/pencil.png"))
            )
        );
        editIcon.setFitHeight(16);
        editIcon.setFitWidth(16);
        editIcon.setPreserveRatio(true);

        Button erase = new Button();
        erase.setGraphic(trashIcon);
        erase.getStyleClass().add("btn-bottom");
        erase.setOnAction((event) -> {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure you want to delete this note?", ButtonType.OK, ButtonType.CANCEL);
            alert.setHeaderText(null);

            Optional<ButtonType> result = alert.showAndWait();
            if (result.isPresent() && result.get() == ButtonType.OK) {
                noteController.notes.removeIf(element -> element.getId() == id);
                noteController.calculateNotes();
            }
        });

        Button edit = new Button();
        edit.setGraphic(editIcon);
        edit.getStyleClass().addAll("btn-bottom", "edit-btn");

        HBox buttons = new HBox(erase, edit);
        HBox dateWrapper = new HBox(addedLabel);
        dateWrapper.setAlignment(Pos.CENTER_LEFT);

        footer.setLeft(dateWrapper);
        footer.setRight(buttons);

        body.getChildren().addAll(titleBox, contentArea, footer);
    }
}
