package com.fitapp.controller;
import com.fitapp.navigation.Navigator;

import com.fitapp.model.EmptyFieldException;
import com.fitapp.model.InvalidCredentialsException;


import com.fitapp.model.UserDatabaseCSV;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.control.PasswordField;
import javafx.scene.control.Label;

public class LoginController implements Controller {

    private Navigator navigator;

    // default constructor for FXML loading
    public LoginController(){

    }

    // Dependency Injection
    @Override
    public void setNavigator(Navigator navigator) {
        this.navigator = navigator;
    }

    @Override
    public void changeView(String fxmlFile) {
        navigator.changeView(fxmlFile);
    }

    // marks fields and methods in a controller so that the FXMLLoader is allowed to access them via reflection and
    // connect them to elements defined in the FXML file. It is the interface between UI and logic
    // allows the FXMLLoader to inject references to UI elements defined in the FXML file into the controller
    // defines which parts of the controller are exposed to the FXML-based view while preserving encapsulation.
    @FXML
    private TextField usernameField;
    @FXML
    private PasswordField passwordField;
    @FXML
    private Label errorLabel;

    private UserDatabaseCSV userDB = new UserDatabaseCSV();

    @FXML
    public void handleLogin(ActionEvent event) {
        try {
            String user = usernameField.getText();
            String pass = passwordField.getText();

            userDB.validateInput(user, pass);

            errorLabel.setVisible(false);
            changeView("mainMenu.fxml");

        } catch (EmptyFieldException e) {
            errorLabel.setText(e.getMessage());
            errorLabel.setVisible(true);

        } catch (InvalidCredentialsException e) {
            errorLabel.setText("Login Failed");
            errorLabel.setVisible(true);
        }
    }

}