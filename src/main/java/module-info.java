module com.inventory {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;

    opens com.inventory to javafx.fxml, javafx.graphics;
    opens com.inventory.controller to javafx.fxml;
    opens com.inventory.model to javafx.fxml;

    exports com.inventory;
    exports com.inventory.controller;
}
