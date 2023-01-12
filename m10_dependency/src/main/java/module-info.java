module be.kdg {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.logging;
    requires java.sql;


    opens be.kdg to javafx.fxml;
}