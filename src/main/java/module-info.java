module com.example.simpleinv {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.simpleinv to javafx.fxml;
    exports com.example.simpleinv;
}