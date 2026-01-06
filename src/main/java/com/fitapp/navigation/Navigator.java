package com.fitapp.navigation;

import com.fitapp.controller.Controller; // your existing controller interface
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Navigator {
    /**
     * The Navigator class is important for dependency injection and makes sure that there is no JavaFX code
     * in the controller classes which adhere to the Dependency Inversion principle of SOLID. This way controllers are
     * dependent on an abstraction (Navigator class - high level module) and not any JavaFX classes (low-level module)
     * */

    private Stage stage;

    // constructor injection
    public Navigator(Stage stage) {
        this.stage = stage;
    }

    public void changeView(String fxmlFile) {
        try {
            // loading a fxml file from resources
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/" + fxmlFile));
            // loading the view tree: parse fxml file, load controller class, instantiate controller class with
            // default constructor, injects @FXML fields
            Parent root = loader.load();

            // Automatic injection of this Navigator into controller
            // JavaFX has already created the controller during load() and this retrieves it
            Object controller = loader.getController();
            // checks if the controller implements the Controller interface and if applicable injects the navigator
            if (controller instanceof Controller ci) {
                ci.setNavigator(this);
            }

            // Set new scene using the loaded UI and assigns it to the stage
            stage.setScene(new Scene(root));
            // display stage
            stage.show();

            // dynamic window title
            switch (fxmlFile) {
                case "mainMenu.fxml" -> stage.setTitle("Menu");
                case "login.fxml" -> stage.setTitle("Login");
                case "caloricIntake.fxml" -> stage.setTitle("Caloric Intake");
                default -> stage.setTitle("App");
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}


