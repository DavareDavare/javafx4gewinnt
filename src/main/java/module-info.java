module com.example.javafx4gewinnt {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.javafx4gewinnt to javafx.fxml;
    exports com.example.javafx4gewinnt;
}