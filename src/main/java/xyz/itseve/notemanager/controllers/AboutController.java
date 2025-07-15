package xyz.itseve.notemanager.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.stage.Stage;

public class AboutController {
    private Stage parentStage;
    public void setParentStage(Stage parentStage) {
        this.parentStage = parentStage;
    }

    @FXML
    private void closeWindow(ActionEvent actionEvent) {
        parentStage.close();
    }
}
