module com.example.freezingtongue {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens com.example.freezingtongue to javafx.fxml;
    exports com.example.freezingtongue;
}