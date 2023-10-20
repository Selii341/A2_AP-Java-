/*
 * The SignController class controls the user login process.
 * It handles user input for signing in and performs authentication.
 * The class interacts with user interface elements for login.
 * It also links to the AccountNumber class for user authentication and data retrieval.
 */

import javafx.event.ActionEvent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.io.IOException;

public class SignController {
    private TextField signAccountTextField;
    private TextField signPasswordTextField;

    private Label signForgetLabel;

    public static Scene signScene;

    public SignController(TextField signAccountTextField, TextField signPasswordTextField, Label signForgetLabel) {
        this.signAccountTextField = signAccountTextField;
        this.signPasswordTextField = signPasswordTextField;
        this.signForgetLabel = signForgetLabel;
    }
    
    /**
     * Event handler for the "OK" button in the sign-in form.
     * Validates user input, attempts to sign in the user, and navigates to the user's dashboard upon success.
     * Displays an error message if sign-in fails.
     *
     * @param event The ActionEvent triggered by clicking the "OK" button.
     */
    public void okButtonHandler(ActionEvent event) {
        try {
            if (AccountNumber.testing(signAccountTextField.getText(), signPasswordTextField.getText()) == 1) {
            	
            	// Set the current user in the HelloApplication
                HelloApplication.currentUser = AccountNumber.getUser(signAccountTextField.getText());
                // Navigate to the user's dashboard
                CenterController.CenterScene = HelloApplication.center();
                HelloApplication.SignStage.setScene(CenterController.CenterScene);
                HelloApplication.SignStage.setTitle("Welcome to Data Analytics Hub, " + HelloApplication.currentUser.getFirstName() + " " + HelloApplication.currentUser.getLastName());
            } else {
            	// Display an error message for failed sign-in
                signForgetLabel.setVisible(true);
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    
    /**
     * Event handler for the "Register" button in the sign-in form.
     * Navigates to the registration page to allow the user to register a new account.
     *
     * @param event The ActionEvent triggered by clicking the "Register" button.
     */
    public void registerHandler(ActionEvent event) {
        RegisterController.registerScene = HelloApplication.register();
        HelloApplication.SignStage.setScene(RegisterController.registerScene);
    }
}
