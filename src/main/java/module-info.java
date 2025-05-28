module com.inventory {
    requires javafx.controls;
    requires javafx.fxml;

    opens com.inventory to javafx.fxml, javafx.graphics;
    exports com.inventory;
}