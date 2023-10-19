/* It represents the View in the MVC pattern (displays the data to the user (GUI)and sends user input to the Controller for processing ) 
 * The application allows users to log in or register new accounts 
 * and handles switching between different scenes (login, registration, and the main content view)
 * User information eg.sss is stored in a file using the accountNumber class.
 */


import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.control.*;

import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

import java.io.*;
import java.util.ArrayList;

public class HelloApplication extends Application {

    //Table to display the posts
    public static TableView tableView;

    public static PieChart pieChart;
    //Current selected Tab
    public static Tab currentTab;

    public static Stage SignStage;
    
    //share to another class
    public static User currentUser;
    @Override
    public void start(Stage stage) throws IOException {

        SignStage = new Stage();
        SignController.signScene = sign();

        SignStage.setTitle("Post Information");
        SignStage.setScene(SignController.signScene);
        SignStage.show();
    }

    //Create a login panel
    Scene sign() {
        Label signTitle = new Label("    Login");
        signTitle.setFont(Font.font("T", FontWeight.LIGHT, FontPosture.ITALIC, 40));
        Label signAccountLabel = new Label("Username:");
        signAccountLabel.setFont(Font.font("T", FontWeight.LIGHT, FontPosture.ITALIC, 20));
        Label signPasswordLabel = new Label("Password:");
        signPasswordLabel.setFont(Font.font("T", FontWeight.LIGHT, FontPosture.ITALIC, 20));
        Label signForgetLabel = new Label("Wrong username or password!");
        signForgetLabel.setTextFill(Color.RED);
        signForgetLabel.setFont(Font.font("T", FontPosture.ITALIC, 20));
        signForgetLabel.setVisible(false);

        TextField signAccountTextField = new TextField();
        TextField signPasswordTextField = new TextField();

        Button signOkButton = new Button("OK");
        Button signRegisterButton = new Button("Sign up");

        GridPane signPane = new GridPane();
        signPane.setHgap(20);
        signPane.setVgap(20);

        signPane.setPadding(new Insets(0, 10, 10, 10));

        signPane.add(signTitle, 4, 2);
        signPane.add(signAccountLabel, 3, 3);
        signPane.add(signAccountTextField, 4, 3);
        signPane.add(signPasswordLabel, 3, 4);
        signPane.add(signPasswordTextField, 4, 4);
        signPane.add(signForgetLabel, 4, 7);
        HBox hbox = new HBox(signOkButton, signRegisterButton);
        hbox.setAlignment(Pos.CENTER);
        hbox.setSpacing(20);

        VBox vbox = new VBox(signPane, hbox);
        Scene scene = new Scene(vbox, 600, 400);

        SignController controller = new SignController(signAccountTextField, signPasswordTextField, signForgetLabel);
        signOkButton.setOnAction(controller::okButtonHandler);

        signRegisterButton.setOnAction(controller::registerHandler);
        return scene;
    }

    //Create a register panel
    public static Scene register() {
        Label registerTitle = new Label("   Register");
        registerTitle.setFont(Font.font("T", FontWeight.LIGHT, FontPosture.ITALIC, 40));
        Label registerAccountLabel = new Label("Username:");
        registerAccountLabel.setFont(Font.font("T", FontWeight.LIGHT, FontPosture.ITALIC, 20));
        Label registerPasswordLabel = new Label("Password:");
        registerPasswordLabel.setFont(Font.font("T", FontWeight.LIGHT, FontPosture.ITALIC, 20));
        Label registerFirstNameLabel = new Label("FirstName:");
        registerFirstNameLabel.setFont(Font.font("T", FontWeight.LIGHT, FontPosture.ITALIC, 20));
        Label registerLastNameLabel = new Label("LastName:");
        registerLastNameLabel.setFont(Font.font("T", FontWeight.LIGHT, FontPosture.ITALIC, 20));

        Label registerRepeatLabel = new Label("      Username existed!    ");
        registerRepeatLabel.setTextFill(Color.RED);
        registerRepeatLabel.setFont(Font.font("T", FontWeight.LIGHT, FontPosture.ITALIC, 20));
        registerRepeatLabel.setVisible(false);

        Label registerSuccessLabel = new Label("Succeed!");
        registerAccountLabel.setTextFill(Color.RED);
        registerAccountLabel.setFont(Font.font("T", FontWeight.LIGHT, FontPosture.ITALIC, 40));
        registerAccountLabel.setVisible(false);

        Button registerOkButton = new Button("OK");
        Button registerReButton = new Button("Back");

        TextField registerAccountTextField = new TextField();
        TextField registerPasswordTextField = new TextField();
        TextField registerFirstNameTextField = new TextField();
        TextField registerLastNameTextField = new TextField();

        GridPane registerPane = new GridPane();
        registerPane.setHgap(20);
        registerPane.setVgap(20);

        registerPane.setPadding(new Insets(0, 10, 10, 10));

        registerPane.add(registerTitle, 4, 2);
        registerPane.add(registerAccountLabel, 3, 3);
        registerPane.add(registerAccountTextField, 4, 3);
        registerPane.add(registerPasswordLabel, 3, 4);
        registerPane.add(registerPasswordTextField, 4, 4);
        registerPane.add(registerRepeatLabel, 4, 7);
        registerPane.add(registerSuccessLabel, 4, 7);

        registerPane.add(registerFirstNameLabel, 3, 5);
        registerPane.add(registerLastNameLabel, 3, 6);
        registerPane.add(registerFirstNameTextField, 4, 5);
        registerPane.add(registerLastNameTextField, 4, 6);

        HBox hbox = new HBox(registerOkButton, registerReButton);
        hbox.setAlignment(Pos.CENTER);
        hbox.setSpacing(20);

        VBox vbox = new VBox(registerPane, hbox);
        Scene scene = new Scene(vbox, 600, 400);

        RegisterController controller = new RegisterController(registerAccountTextField, registerPasswordTextField, registerFirstNameTextField, registerLastNameTextField, registerSuccessLabel, registerRepeatLabel);
        registerReButton.setOnAction(controller::backButtonHandler);
        registerOkButton.setOnAction(controller::okButtonHandler);
        return scene;
    }

    public static Scene center() {
        //Create a table to display
        tableView = CenterController.createTable();
        TabPane tabPane = initialTab();

        //text for typing the search string
        TextField field = new TextField();
        field.setPrefSize(120, 20);
        field.setAlignment(Pos.CENTER_LEFT);
        field.setEditable(true);

        //text for typing delete id
        TextField deleteField = new TextField();
        field.setPrefSize(120, 20);
        field.setAlignment(Pos.CENTER_LEFT);
        field.setEditable(true);

        //text of number N
        TextField numField = new TextField();
        field.setPrefSize(120, 20);
        field.setAlignment(Pos.CENTER_LEFT);
        field.setEditable(true);

        //text of exportID
        TextField exportIDField = new TextField();
        field.setPrefSize(120, 20);
        field.setAlignment(Pos.CENTER_LEFT);
        exportIDField.setText("ID");
        field.setEditable(true);

        //text of export file name
        TextField exportFileField = new TextField();
        field.setPrefSize(120, 20);
        field.setAlignment(Pos.CENTER_LEFT);
        exportFileField.setText("FileName");
        field.setEditable(true);


        CenterController controller = new CenterController(field, deleteField, numField, exportIDField, exportFileField);

        //Click Add button and add a post
        Button addButton = new Button("Add Post");
        addButton.setOnAction(controller::addButtonHandler);

        //Click search button and search the post based on id
        Button searchButton = new Button("Search");
        searchButton.setOnAction(controller::searchButtonHandler);

        //Click edit button to edit profile
        Button editButton = new Button("Edit profile");
        editButton.setOnAction(controller::editButtonHandler);

        //Click Remove Button to remove a post
        Button removeButton = new Button("Delete Post");
        removeButton.setOnAction(controller::removeButtonHandler);

        //Click likes Button to retrieve topN posts
        Button likesButton = new Button("TopN like");
        likesButton.setOnAction(controller::likesButtonHandler);

        //Click export Button to export a post to file
        Button exportButton = new Button("Export to csv");
        exportButton.setOnAction(controller::exportButtonHandler);

        //Click Logout Button to logout
        Button logoutButton = new Button("Log out");
        logoutButton.setOnAction(controller::logoutButtonHandler);

        //Click Upgrade Button to Upgrade Vip
        Button upgradeButton = new Button("Upgrade");
        upgradeButton.setOnAction(controller::upgradeButtonHandler);

        pieChart = updateVIPComponent();

        //VBox for all components
        HBox searchBox = new HBox(field, searchButton, addButton, editButton, upgradeButton, logoutButton);
        HBox deleteBox = new HBox(deleteField, removeButton);
        HBox likesBox = new HBox(numField, likesButton);
        HBox exportBox = new HBox(exportIDField, exportFileField, exportButton);
        searchBox.setPadding(new Insets(5));
        searchBox.setSpacing(5);
        deleteBox.setPadding(new Insets(5));
        deleteBox.setSpacing(5);
        likesBox.setPadding(new Insets(5));
        likesBox.setSpacing(5);
        exportBox.setPadding(new Insets(5));
        exportBox.setSpacing(5);
        VBox vbox = new VBox(searchBox, deleteBox, likesBox, tabPane, exportBox, pieChart);
        Scene scene;

        //If it is vip
        if(HelloApplication.currentUser.isVip()) {
            pieChart.setVisible(true);
            //transparent the button
            upgradeButton.setVisible(false);
            //collapse the button
            upgradeButton.setManaged(false);
            scene = new Scene(vbox, 1200, 800);
        } else {
            pieChart.setVisible(false);
            pieChart.setManaged(false);
            upgradeButton.setVisible(true);
            scene = new Scene(vbox, 640, 400);
        }

        return scene;
    }

    // piechart creation
    public static PieChart updateVIPComponent() {
        PieChart p = new PieChart();
        ArrayList<Integer> shareMap = PostsCollection.getSharesMap();
        //delve into 3 slice <100,<1000,>1000
        PieChart.Data slice1 = new PieChart.Data("shares: 0-99", shareMap.get(0));
        PieChart.Data slice2 = new PieChart.Data("shares: 100-999", shareMap.get(1));
        PieChart.Data slice3 = new PieChart.Data("shares: 1000+", shareMap.get(2));
        p.getData().add(slice1);
        p.getData().add(slice2);
        p.getData().add(slice3);

        return p;
    }

    public static TabPane initialTab() {
        TabPane tabPane = new TabPane();

        tabPane.setPrefWidth(400);
        tabPane.setPrefHeight(400);

        Tab tab1 = new Tab("Posts");
        tab1.setContent(tableView);
        tabPane.getTabs().addAll(tab1);

        currentTab = tab1;

        return tabPane;
    }


    public static void main(String[] args) {
        launch();
    }
}