/*
 * */

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
//Press ok to signin 
    public void okButtonHandler(ActionEvent event) {
        try {
            if (AccountNumber.testing(signAccountTextField.getText(), signPasswordTextField.getText()) == 1) {
            	//crab the static currentUser from HelloApplication
                HelloApplication.currentUser = AccountNumber.getUser(signAccountTextField.getText());
                CenterController.CenterScene = HelloApplication.center();
                HelloApplication.SignStage.setScene(CenterController.CenterScene);
                HelloApplication.SignStage.setTitle("Welcome " + HelloApplication.currentUser.getFirstName() + " " + HelloApplication.currentUser.getLastName());
            } else {
                signForgetLabel.setVisible(true);
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
//Another option to register
    public void registerHandler(ActionEvent event) {
        RegisterController.registerScene = HelloApplication.register();
        HelloApplication.SignStage.setScene(RegisterController.registerScene);
    }
}
