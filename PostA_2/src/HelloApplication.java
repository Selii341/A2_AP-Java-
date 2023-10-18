

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
        SignController.SignScene = Sign();

        SignStage.setTitle("Post Information");
        SignStage.setScene(SignController.SignScene);
        SignStage.show();
    }

    //Create a login panel
    Scene Sign() {
        Label SignTitle = new Label("    Login");
        SignTitle.setFont(Font.font("T", FontWeight.LIGHT, FontPosture.ITALIC, 40));
        Label SignAccountLabel = new Label("Username:");
        SignAccountLabel.setFont(Font.font("T", FontWeight.LIGHT, FontPosture.ITALIC, 20));
        Label SignPasswordLabel = new Label("Password:");
        SignPasswordLabel.setFont(Font.font("T", FontWeight.LIGHT, FontPosture.ITALIC, 20));
        Label SignForgetLabel = new Label("Wrong username or password!");
        SignForgetLabel.setTextFill(Color.RED);
        SignForgetLabel.setFont(Font.font("T", FontPosture.ITALIC, 20));
        SignForgetLabel.setVisible(false);

        TextField SignAccountTextField = new TextField();
        TextField SignPasswordTextField = new TextField();

        Button SignOkButton = new Button("OK");
        Button SignRegisterButton = new Button("Sign up");

        GridPane SignPane = new GridPane();
        SignPane.setHgap(20);
        SignPane.setVgap(20);

        SignPane.setPadding(new Insets(0, 10, 10, 10));

        SignPane.add(SignTitle, 4, 2);
        SignPane.add(SignAccountLabel, 3, 3);
        SignPane.add(SignAccountTextField, 4, 3);
        SignPane.add(SignPasswordLabel, 3, 4);
        SignPane.add(SignPasswordTextField, 4, 4);
        SignPane.add(SignForgetLabel, 4, 7);
        HBox hbox = new HBox(SignOkButton, SignRegisterButton);
        hbox.setAlignment(Pos.CENTER);
        hbox.setSpacing(20);

        VBox vbox = new VBox(SignPane, hbox);
        Scene scene = new Scene(vbox, 600, 400);

        SignController controller = new SignController(SignAccountTextField, SignPasswordTextField, SignForgetLabel);
        SignOkButton.setOnAction(controller::okButtonHandler);

        SignRegisterButton.setOnAction(controller::registerHandler);
        return scene;
    }

    //Create a register panel
    public static Scene Register() {
        Label RegisterTitle = new Label("   Register");
        RegisterTitle.setFont(Font.font("T", FontWeight.LIGHT, FontPosture.ITALIC, 40));
        Label RegisterAccountLabel = new Label("Username:");
        RegisterAccountLabel.setFont(Font.font("T", FontWeight.LIGHT, FontPosture.ITALIC, 20));
        Label RegisterPasswordLabel = new Label("Password:");
        RegisterPasswordLabel.setFont(Font.font("T", FontWeight.LIGHT, FontPosture.ITALIC, 20));
        Label RegisterFirstNameLabel = new Label("FirstName:");
        RegisterFirstNameLabel.setFont(Font.font("T", FontWeight.LIGHT, FontPosture.ITALIC, 20));
        Label RegisterLastNameLabel = new Label("LastName:");
        RegisterLastNameLabel.setFont(Font.font("T", FontWeight.LIGHT, FontPosture.ITALIC, 20));

        Label RegisterRepeatLabel = new Label("      Username existed!    ");
        RegisterRepeatLabel.setTextFill(Color.RED);
        RegisterRepeatLabel.setFont(Font.font("T", FontWeight.LIGHT, FontPosture.ITALIC, 20));
        RegisterRepeatLabel.setVisible(false);

        Label RegisterSuccessLabel = new Label("Succeed!");
        RegisterSuccessLabel.setTextFill(Color.RED);
        RegisterSuccessLabel.setFont(Font.font("T", FontWeight.LIGHT, FontPosture.ITALIC, 40));
        RegisterSuccessLabel.setVisible(false);

        Button RegisterOkButton = new Button("OK");
        Button RegisterReButton = new Button("Back");

        TextField RegisterAccountTextField = new TextField();
        TextField RegisterPasswordTextField = new TextField();
        TextField RegisterFirstNameTextField = new TextField();
        TextField RegisterLastNameTextField = new TextField();

        GridPane RegisterPane = new GridPane();
        RegisterPane.setHgap(20);
        RegisterPane.setVgap(20);

        RegisterPane.setPadding(new Insets(0, 10, 10, 10));

        RegisterPane.add(RegisterTitle, 4, 2);
        RegisterPane.add(RegisterAccountLabel, 3, 3);
        RegisterPane.add(RegisterAccountTextField, 4, 3);
        RegisterPane.add(RegisterPasswordLabel, 3, 4);
        RegisterPane.add(RegisterPasswordTextField, 4, 4);
        RegisterPane.add(RegisterRepeatLabel, 4, 7);
        RegisterPane.add(RegisterSuccessLabel, 4, 7);

        RegisterPane.add(RegisterFirstNameLabel, 3, 5);
        RegisterPane.add(RegisterLastNameLabel, 3, 6);
        RegisterPane.add(RegisterFirstNameTextField, 4, 5);
        RegisterPane.add(RegisterLastNameTextField, 4, 6);

        HBox hbox = new HBox(RegisterOkButton, RegisterReButton);
        hbox.setAlignment(Pos.CENTER);
        hbox.setSpacing(20);

        VBox vbox = new VBox(RegisterPane, hbox);
        Scene scene = new Scene(vbox, 600, 400);

        RegisterController controller = new RegisterController(RegisterAccountTextField, RegisterPasswordTextField, RegisterFirstNameTextField, RegisterLastNameTextField, RegisterSuccessLabel, RegisterRepeatLabel);
        RegisterReButton.setOnAction(controller::backButtonHandler);
        RegisterOkButton.setOnAction(controller::okButtonHandler);
        return scene;
    }

    public static Scene Center() {
        //Create a table to display
        tableView = CenterController.CreateTable();
        TabPane tabPane = InitialTab();

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
        Button EditButton = new Button("Edit profile");
        EditButton.setOnAction(controller::editButtonHandler);

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
        HBox searchBox = new HBox(field, searchButton, addButton, EditButton, upgradeButton, logoutButton);
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

    public static TabPane InitialTab() {
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