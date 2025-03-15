module com.example.baseballscoringapplication {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;


    opens com.baseballscoringapplication to javafx.fxml;
    exports com.baseballscoringapplication;

    opens com.baseballscoringapplication.gameComponents to javafx.fxml;
    exports com.baseballscoringapplication.gameComponents;

    opens com.baseballscoringapplication.controllers to javafx.fxml;
    exports com.baseballscoringapplication.controllers;

    opens com.baseballscoringapplication.managers to javafx.fxml;
    exports com.baseballscoringapplication.managers;
    exports com.baseballscoringapplication.gameComponents.Play;
    opens com.baseballscoringapplication.gameComponents.Play to javafx.fxml;

}