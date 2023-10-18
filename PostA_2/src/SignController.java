/*
 * */

import javafx.event.ActionEvent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.io.IOException;

public class SignController {
    private TextField SignAccountTextField;
    private TextField SignPasswordTextField;

    private Label SignForgetLabel;

    public static Scene SignScene;

    public SignController(TextField signAccountTextField, TextField signPasswordTextField, Label signForgetLabel) {
        SignAccountTextField = signAccountTextField;
        SignPasswordTextField = signPasswordTextField;
        SignForgetLabel = signForgetLabel;
    }

    public void okButtonHandler(ActionEvent event) {
        try {
            if (accountNumber.testing(SignAccountTextField.getText(), SignPasswordTextField.getText()) == 1) {
            	//crab the static currentUser from HelloApplication
                HelloApplication.currentUser = accountNumber.getUser(SignAccountTextField.getText());
                CenterController.CenterScene = HelloApplication.Center();
                HelloApplication.SignStage.setScene(CenterController.CenterScene);
                HelloApplication.SignStage.setTitle("Welcome " + HelloApplication.currentUser.getFirstName() + " " + HelloApplication.currentUser.getLastName());
            } else {
                SignForgetLabel.setVisible(true);
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public void registerHandler(ActionEvent event) {
        RegisterController.RegisterScene = HelloApplication.Register();
        HelloApplication.SignStage.setScene(RegisterController.RegisterScene);
    }
}
