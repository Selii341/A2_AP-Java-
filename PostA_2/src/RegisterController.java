

import javafx.event.ActionEvent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.io.IOException;

public class RegisterController {
    private TextField RegisterAccountTextField;
    private TextField RegisterPasswordTextField;
    private TextField RegisterFirstNameTextField;
    private TextField RegisterLastNameTextField;
    private Label RegisterSuccessLabel;
    private Label RegisterRepeatLabel;

    public static Scene RegisterScene;

    public RegisterController(TextField registerAccountTextField, TextField registerPasswordTextField, TextField registerFirstNameTextField, TextField registerLastNameTextField, Label registerSuccessLabel, Label registerRepeatLabel) {
        RegisterAccountTextField = registerAccountTextField;
        RegisterPasswordTextField = registerPasswordTextField;
        RegisterFirstNameTextField = registerFirstNameTextField;
        RegisterLastNameTextField = registerLastNameTextField;
        RegisterSuccessLabel = registerSuccessLabel;
        RegisterRepeatLabel = registerRepeatLabel;
    }

    public void okButtonHandler(ActionEvent event) {
        if (RegisterAccountTextField.getText().isEmpty() || RegisterPasswordTextField.getText().isEmpty() || RegisterFirstNameTextField.getText().isEmpty() ||
                RegisterLastNameTextField.getText().isEmpty()) {
            Alert a = new Alert(Alert.AlertType.WARNING, "Information should not be empty");
            a.setTitle("Warning");
            a.setHeaderText("Field Empty");
            a.show();
            return;
        }
        try {
            if (accountNumber.testing(RegisterAccountTextField.getText()) == 1) {
                accountNumber.register(RegisterAccountTextField.getText(), RegisterPasswordTextField.getText(),
                        RegisterFirstNameTextField.getText(), RegisterLastNameTextField.getText());
                HelloApplication.SignStage.setScene(SignController.SignScene);
                Alert a = new Alert(Alert.AlertType.INFORMATION, "Register succeeded");
                a.setTitle("Information");
                a.setHeaderText("Register succeeded");
                a.show();
            } else {
                RegisterRepeatLabel.setVisible(true);
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public void backButtonHandler(ActionEvent event) {
        HelloApplication.SignStage.setScene(SignController.SignScene);
    }
}
