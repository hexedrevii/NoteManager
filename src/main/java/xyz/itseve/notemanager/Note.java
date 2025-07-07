package xyz.itseve.notemanager;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;
import java.util.UUID;

public class Note {
    private String title;
    private String content;

    private final LocalDateTime added;
    private final UUID id;

    private VBox body;
    public final VBox getBody() {
        return body;
    }

    public Note(String title, String content) {
        this.title = title;
        this.content = content;

        added = LocalDateTime.now();
        id = UUID.randomUUID();

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

        HBox titleBox = new HBox(titleLabel);
        titleBox.setAlignment(Pos.CENTER);

        TextArea contentArea = new TextArea(content);
        contentArea.setId("cnt");
        contentArea.setWrapText(true);
        contentArea.setPrefColumnCount(4);
        VBox.setVgrow(contentArea, Priority.ALWAYS);

        Label addedLabel = new Label("Added on " + added.format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss")));
        addedLabel.setId("adl");

        body.getChildren().addAll(titleBox, contentArea, addedLabel);
    }
}
