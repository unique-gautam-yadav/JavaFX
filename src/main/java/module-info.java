module com.example.mmw {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens com.example.mmw to javafx.fxml;
    exports com.example.mmw;
}