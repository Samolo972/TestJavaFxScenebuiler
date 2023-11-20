module com.example.exercice1 {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.exercice1 to javafx.fxml;
    exports com.example.exercice1;
}