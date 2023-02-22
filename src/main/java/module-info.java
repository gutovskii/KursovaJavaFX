module com.example.kursova {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires mysql.connector.j;


    exports com.example.kursova;
    opens com.example.kursova to javafx.fxml;
    exports com.example.kursova.controllers;
    opens com.example.kursova.controllers to javafx.fxml;
}