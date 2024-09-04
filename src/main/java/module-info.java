module com.example.sistemasop {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.sistemasop to javafx.fxml;
    exports com.example.sistemasop;
}