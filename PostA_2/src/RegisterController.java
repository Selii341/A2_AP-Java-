
/*
 * The RegisterController class is responsible for controlling the user registration process.
 * It handles user input for creating new accounts and verifies the input data.
 * The class interacts with user interface elements for registration.
 * It links to the AccountNumber class for registration and user account management.
 */
import javafx.event.ActionEvent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.io.IOException;

public class RegisterController {
	// Text fields for user registration information
    private TextField registerAccountTextField;
    private TextField registerPasswordTextField;
    private TextField registerFirstNameTextField;
    private TextField registerLastNameTextField;
 // Labels to display registration-related messages
    private Label registerSuccessLabel;
    private Label registerRepeatLabel;
 // A static reference to the registration scene
    public static Scene registerScene;

    public RegisterController(TextField registerAccountTextField, TextField registerPasswordTextField, TextField registerFirstNameTextField, TextField registerLastNameTextField, Label registerSuccessLabel, Label registerRepeatLabel) {
        this.registerAccountTextField = registerAccountTextField;
        this.registerPasswordTextField = registerPasswordTextField;
        this.registerFirstNameTextField = registerFirstNameTextField;
        this.registerLastNameTextField = registerLastNameTextField;
        this.registerSuccessLabel = registerSuccessLabel;
        this.registerRepeatLabel = registerRepeatLabel;
    }
    /**
     * Event handler for the "OK" button in the registration form.
     * Validates user input, registers a new user account, and handles success or repeat registration.
     *
     * @param event The ActionEvent triggered by clicking the "OK" button.
     */
    public void okButtonHandler(ActionEvent event) {
        if (registerAccountTextField.getText().isEmpty() || registerPasswordTextField.getText().isEmpty() || registerFirstNameTextField.getText().isEmpty() ||
                registerLastNameTextField.getText().isEmpty()) {
        	// Show a warning if any registration fields are empty
            Alert a = new Alert(Alert.AlertType.WARNING, "Information should not be empty");
            a.setTitle("Warning");
            a.setHeaderText("Field Empty");
            a.show();
            return;
        }
        try {
        	// Check if the username already exists
            if (AccountNumber.testing(registerAccountTextField.getText()) == 1) {
            	// If not, register the user
                AccountNumber.register(registerAccountTextField.getText(), registerPasswordTextField.getText(),
                        registerFirstNameTextField.getText(), registerLastNameTextField.getText());
                HelloApplication.SignStage.setScene(SignController.signScene);
             // Show a success message
                Alert a = new Alert(Alert.AlertType.INFORMATION, "Register succeeded");
                a.setTitle("Information");
                a.setHeaderText("Register succeeded");
                a.show();
            } else {
            	 // Display a message indicating that the username already exists
                registerRepeatLabel.setVisible(true);
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    
    /**
     * Event handler for the "Back" button in the registration form.
     * Navigates back to the previous page (the sign-in page).
     *
     * @param event The ActionEvent triggered by clicking the "Back" button.
     */
    public void backButtonHandler(ActionEvent event) {
        HelloApplication.SignStage.setScene(SignController.signScene);
    }
}
