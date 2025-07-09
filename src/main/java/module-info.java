module xyz.itseve.notemanager {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;

    opens xyz.itseve.notemanager to javafx.fxml;
    exports xyz.itseve.notemanager;
    exports xyz.itseve.notemanager.controllers;
    opens xyz.itseve.notemanager.controllers to javafx.fxml;
}