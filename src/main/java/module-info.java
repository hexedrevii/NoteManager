module xyz.itseve.notemanager {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires com.fasterxml.jackson.annotation;
    requires com.fasterxml.jackson.databind;

    opens xyz.itseve.notemanager to javafx.fxml;
    exports xyz.itseve.notemanager;
    exports xyz.itseve.notemanager.controllers;
    exports xyz.itseve.notemanager.models to com.fasterxml.jackson.databind;
    opens xyz.itseve.notemanager.controllers to javafx.fxml;
}