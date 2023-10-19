

import javafx.event.ActionEvent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.io.IOException;

public class RegisterController {
    private TextField registerAccountTextField;
    private TextField registerPasswordTextField;
    private TextField registerFirstNameTextField;
    private TextField registerLastNameTextField;
    private Label registerSuccessLabel;
    private Label registerRepeatLabel;

    public static Scene registerScene;

    public RegisterController(TextField registerAccountTextField, TextField registerPasswordTextField, TextField registerFirstNameTextField, TextField registerLastNameTextField, Label registerSuccessLabel, Label registerRepeatLabel) {
        this.registerAccountTextField = registerAccountTextField;
        this.registerPasswordTextField = registerPasswordTextField;
        this.registerFirstNameTextField = registerFirstNameTextField;
        this.registerLastNameTextField = registerLastNameTextField;
        this.registerSuccessLabel = registerSuccessLabel;
        this.registerRepeatLabel = registerRepeatLabel;
    }
//Press ok to register and verify the state
    public void okButtonHandler(ActionEvent event) {
        if (registerAccountTextField.getText().isEmpty() || registerPasswordTextField.getText().isEmpty() || registerFirstNameTextField.getText().isEmpty() ||
                registerLastNameTextField.getText().isEmpty()) {
            Alert a = new Alert(Alert.AlertType.WARNING, "Information should not be empty");
            a.setTitle("Warning");
            a.setHeaderText("Field Empty");
            a.show();
            return;
        }
        try {
            if (AccountNumber.testing(registerAccountTextField.getText()) == 1) {
                AccountNumber.register(registerAccountTextField.getText(), registerPasswordTextField.getText(),
                        registerFirstNameTextField.getText(), registerLastNameTextField.getText());
                HelloApplication.SignStage.setScene(SignController.signScene);
                Alert a = new Alert(Alert.AlertType.INFORMATION, "Register succeeded");
                a.setTitle("Information");
                a.setHeaderText("Register succeeded");
                a.show();
            } else {
            	//already exist
                registerRepeatLabel.setVisible(true);
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
//Back to the previous page
    public void backButtonHandler(ActionEvent event) {
        HelloApplication.SignStage.setScene(SignController.signScene);
    }
}
