

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.*;

import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class HelloApplication extends Application {

    //Table to display the posts
    private TableView tableView;
    //Current selected Tab
    private Tab currentTab;

    Stage SignStage = new Stage();
    GridPane SignPane = new GridPane();
    Scene SignScene;
    GridPane RegisterPane = new GridPane();
    Scene RegisterScene;
    GridPane CenterPane = new GridPane();
    Scene CenterScene;

    String currentAccount = "";
    User currentUser;
    @Override
    public void start(Stage stage) throws IOException {


        SignScene = Sign();

        SignStage.setTitle("Post Information");
        SignStage.setScene(SignScene);
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

        SignOkButton.setOnAction(e -> {
            try {
                if (accountNumber.testing(SignAccountTextField.getText(), SignPasswordTextField.getText()) == 1) {
                    currentUser = accountNumber.getUser(SignAccountTextField.getText());
                    CenterScene = Center();
                    SignStage.setScene(CenterScene);
                    SignStage.setTitle("Welcome " + currentUser.getFirstName() + " " + currentUser.getLastName());
                } else {
                    SignForgetLabel.setVisible(true);
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        });

        SignRegisterButton.setOnAction(e -> {
            RegisterScene = Register();
            SignStage.setScene(RegisterScene);
        });
        return scene;
    }

    //Create a register panel
    Scene Register() {
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

        RegisterReButton.setOnAction(e -> SignStage.setScene(SignScene));

        RegisterOkButton.setOnAction(e -> {
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
                    RegisterSuccessLabel.setVisible(true);
                    RegisterRepeatLabel.setVisible(false);
                } else
                    RegisterRepeatLabel.setVisible(true);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        });
        return scene;
    }

    Scene Center() {
        //Create a table to display
        tableView = CreateTable();
        TabPane tabPane = InitialTab();

        //text for typing the search string
        TextField field = new TextField();
        field.setPrefSize(120, 20);
        field.setAlignment(Pos.CENTER_LEFT);
        field.setEditable(true);

        //Click Add button and add a post
        Button addButton = new Button("+");
        addButton.setOnAction(e -> {
            AddPost();
        });

        //Click search button and search the post based on id
        Button searchButton = new Button("Search");
        searchButton.setOnAction(e -> {
            tableView.getItems().clear();
            if (field.getText().equals("")) {
                tableView = CreateTable();
                currentTab.setContent(tableView);
                return;
            }
            try {
                int id = Integer.valueOf(field.getText());
                for(Post p : PostsCollection.getPostMap().values()) {
                    if (p.getID() == id) {
                        tableView.getItems().add(p);
                    }
                }
            } catch (NumberFormatException ex) {
                Alert a = new Alert(Alert.AlertType.WARNING, "ID should be integer");
                a.setTitle("Warning");
                a.setHeaderText("Type error");
                a.show();
            }

        });

        //Click edit button to edit profile
        Button EditButton = new Button("Edit");
        EditButton.setOnAction(e -> {
            EditProfile();
        });

        //VBox for all components
        HBox searchBox = new HBox(field, searchButton, addButton, EditButton);
        searchBox.setPadding(new Insets(5));
        searchBox.setSpacing(5);
        VBox vbox = new VBox(searchBox, tabPane);
        Scene scene = new Scene(vbox, 640, 400);
        return scene;
    }


    public TabPane InitialTab() {
        TabPane tabPane = new TabPane();

        tabPane.setPrefWidth(400);
        tabPane.setPrefHeight(400);

        Tab tab1 = new Tab("Posts");
        tab1.setContent(tableView);
        tabPane.getTabs().addAll(tab1);

        currentTab = tab1;

        return tabPane;
    }

    public static HashMap<Integer, Post> LoadFiles() {
        HashMap<Integer, Post> result = new HashMap<Integer, Post>();
        try {
            FileReader fr = new FileReader("posts.csv");
            BufferedReader br = new BufferedReader(fr);

            String line = br.readLine();
            int i = 0;
            //title

            while(line != null)
            {
                if (i == 0) {
                    i++;
                    line = br.readLine();
                    continue;
                }
                List<String> lineData = Arrays.asList(line.split(","));
                //System.out.println(lineData);

                //string convert to date
                SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
                Date d = sdf.parse(lineData.get(5));
                //Construct a new post and add to array list result
                Post p = new Post(Integer.valueOf(lineData.get(0)), lineData.get(1), lineData.get(2),
                        Integer.valueOf(lineData.get(3)), Integer.valueOf(lineData.get(4)), d);

                result.put(p.getID(), p);

                //read next line
                line = br.readLine();
            }
        } catch (FileNotFoundException e) {
            System.out.println("posts.csv file not found.");
        } catch (IOException e) {
            System.out.println("Invalid file content, read failed.");
        } catch (ParseException e) {
            System.out.println("Invalid file content, mismatch with post's structure.");
        } catch (IllegalArgumentException  e) {
            System.out.println("Invalid date format in file. It has to be dd/MM/yyyy HH:mm");
        }
        return result;
    }

    public TableView CreateTable() {
        PostsCollection.initPosts(LoadFiles());
        TableView tableView = new TableView();

        //Create 5 columns and connect with each property in Student class
        TableColumn<Post, String> column1 = new TableColumn<>("ID");
        column1.setCellValueFactory(new PropertyValueFactory<>("ID"));

        TableColumn<Post, String> column2 = new TableColumn<>("Content");
        column2.setCellValueFactory(new PropertyValueFactory<>("content"));

        TableColumn<Post, String> column3 = new TableColumn<>("Author");
        column3.setCellValueFactory(new PropertyValueFactory<>("author"));

        TableColumn<Post, String> column4 = new TableColumn<>("Likes");
        column4.setCellValueFactory(new PropertyValueFactory<>("likes"));

        TableColumn<Post, String> column5 = new TableColumn<>("Shares");
        column5.setCellValueFactory(new PropertyValueFactory<>("shares"));

        TableColumn<Post, Date> column6 = new TableColumn<>("Date");
        column6.setCellFactory(column -> {
            TableCell<Post, Date> cell = new TableCell<Post, Date>() {
                private SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy HH:mm");
                @Override
                protected void updateItem(Date item, boolean empty) {
                    super.updateItem(item, empty);
                    if(empty) {
                        setText(null);
                    }
                    else {
                        this.setText(format.format(item));

                    }
                }
            };
            return cell;
        });
        column6.setCellValueFactory(new PropertyValueFactory<>("date"));

        //Add columns to table
        tableView.getColumns().clear();
        tableView.getColumns().add(column1);
        tableView.getColumns().add(column2);
        tableView.getColumns().add(column3);
        tableView.getColumns().add(column4);
        tableView.getColumns().add(column5);
        tableView.getColumns().add(column6);

        //Add rows to table
        tableView.getItems().clear();
        for(Post p : PostsCollection.getPostMap().values()) {
            tableView.getItems().add(p);
        }

        return tableView;
    }



    //Add a new student to selected class
    public void AddPost() {
        Stage msgBox = new Stage();
        Group msgGroup = new Group();
        Scene scene1 = new Scene(msgGroup,300, 450);

        //label
        Label idLabel = new Label("ID");
        initAddLabel(idLabel, 10, 10);
        Label contentLabel = new Label("Content");
        initAddLabel(contentLabel, 10, 70);
        Label authorLabel = new Label("Author");
        initAddLabel(authorLabel, 10, 130);
        Label likesLabel = new Label("Likes");
        initAddLabel(likesLabel, 10, 190);
        Label sharesLabel = new Label("Shares");
        initAddLabel(sharesLabel, 10, 250);

        //field
        TextField idField = new TextField();
        initAddField(idField, 100, 15);
        TextField contentField = new TextField();
        initAddField(contentField, 100, 75);
        TextField authorField = new TextField();
        initAddField(authorField, 100, 135);
        TextField likesField = new TextField();
        initAddField(likesField, 100, 195);
        TextField sharesField = new TextField();
        initAddField(sharesField, 100, 255);

        msgGroup.getChildren().add(idLabel);
        msgGroup.getChildren().add(contentLabel);
        msgGroup.getChildren().add(authorLabel);
        msgGroup.getChildren().add(likesLabel);
        msgGroup.getChildren().add(sharesLabel);
        msgGroup.getChildren().add(idField);
        msgGroup.getChildren().add(contentField);
        msgGroup.getChildren().add(authorField);
        msgGroup.getChildren().add(likesField);
        msgGroup.getChildren().add(sharesField);

        Button confirmButton = new Button("Confirm");
        confirmButton.setOnAction(e -> {
            if (idField.getText().isEmpty() || contentField.getText().isEmpty() || authorField.getText().isEmpty() ||
                    likesField.getText().isEmpty() || sharesField.getText().isEmpty()) {
                Alert a = new Alert(Alert.AlertType.WARNING, "Information should not be empty");
                a.setTitle("Warning");
                a.setHeaderText("Field Empty");
                a.show();
                return;
            }
            try {

                SimpleDateFormat dateFormat= new SimpleDateFormat("dd/MM/YYYY HH:MM");
                System.out.println(dateFormat.format(new Date()));
                Post p = new Post(Integer.valueOf(idField.getText()), contentField.getText(), authorField.getText(),
                        Integer.valueOf(likesField.getText()), Integer.valueOf(sharesField.getText()), new Date());
                SavePostToFile(p);
                tableView = CreateTable();
                currentTab.setContent(tableView);
                msgBox.close();
            } catch(Exception exception) {
                Alert a = new Alert(Alert.AlertType.WARNING, "ID, Likes and Shares should be integer");
                a.setTitle("Warning");
                a.setHeaderText("Type error");
                a.show();
            }

        });

        msgGroup.getChildren().add(confirmButton);
        confirmButton.setPrefSize(120, 30);
        confirmButton.setLayoutX(100);
        confirmButton.setLayoutY(290);
        confirmButton.setFont(new Font(20));
        msgBox.setTitle("Add a new post");
        msgBox.setScene(scene1);
        msgBox.show();
    }

    private void initAddField(TextField field, int layoutX, int layoutY) {
        field.setPrefSize(150, 20);
        field.setAlignment(Pos.CENTER_LEFT);
        field.setEditable(true);
        field.setLayoutX(layoutX);
        field.setLayoutY(layoutY);
    }

    private void initAddLabel(Label label, int layoutX, int layoutY) {
        label.setLayoutX(layoutX);
        label.setLayoutY(layoutY);
        label.setFont(new Font(20));
    }

    private void SavePostToFile(Post p) {
        File file = new File("posts.csv");
        BufferedWriter out = null;
        try {

            SimpleDateFormat dateFormat= new SimpleDateFormat("dd/MM/YYYY HH:MM");

            out = new BufferedWriter(new OutputStreamWriter(
                    new FileOutputStream(file, true)));
            String content = p.getID() + "," + p.getContent() + "," + p.getAuthor()
                    + "," + p.getLikes() + "," + p.getShares() + "," + dateFormat.format(p.getDate()) + "\n";
            out.write(content);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                out.flush();
                out.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    void EditProfile() {
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

        Button RegisterOkButton = new Button("OK");
        Button RegisterReButton = new Button("Back");

        TextField RegisterAccountTextField = new TextField();
        TextField RegisterPasswordTextField = new TextField();
        TextField RegisterFirstNameTextField = new TextField();
        TextField RegisterLastNameTextField = new TextField();
        RegisterAccountTextField.setEditable(false);
        RegisterAccountTextField.setText(currentUser.getUsername());

        GridPane EditPane = new GridPane();
        EditPane.setHgap(20);
        EditPane.setVgap(20);

        EditPane.setPadding(new Insets(0, 10, 10, 10));

        EditPane.add(RegisterTitle, 4, 2);
        EditPane.add(RegisterAccountLabel, 3, 3);
        EditPane.add(RegisterAccountTextField, 4, 3);
        EditPane.add(RegisterPasswordLabel, 3, 4);
        EditPane.add(RegisterPasswordTextField, 4, 4);

        EditPane.add(RegisterFirstNameLabel, 3, 5);
        EditPane.add(RegisterLastNameLabel, 3, 6);
        EditPane.add(RegisterFirstNameTextField, 4, 5);
        EditPane.add(RegisterLastNameTextField, 4, 6);

        HBox hbox = new HBox(RegisterOkButton, RegisterReButton);
        hbox.setAlignment(Pos.CENTER);
        hbox.setSpacing(20);

        VBox vbox = new VBox(EditPane, hbox);
        Scene scene = new Scene(vbox, 600, 400);
        SignStage.setScene(scene);

        RegisterReButton.setOnAction(e -> {
            SignStage.setScene(CenterScene);
        });

        RegisterOkButton.setOnAction(e -> {
            if (RegisterAccountTextField.getText().isEmpty() || RegisterPasswordTextField.getText().isEmpty() || RegisterFirstNameTextField.getText().isEmpty() ||
                    RegisterLastNameTextField.getText().isEmpty()) {
                Alert a = new Alert(Alert.AlertType.WARNING, "Information should not be empty");
                a.setTitle("Warning");
                a.setHeaderText("Field Empty");
                a.show();
                return;
            }
            try {
                accountNumber.Edit(RegisterAccountTextField.getText(), RegisterPasswordTextField.getText(),
                        RegisterFirstNameTextField.getText(), RegisterLastNameTextField.getText());
                SignStage.setScene(CenterScene);
                currentUser = accountNumber.getUser(RegisterAccountTextField.getText());
                SignStage.setTitle("Welcome " + currentUser.getFirstName() + " " + currentUser.getLastName());
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        });
    }


    public static void main(String[] args) {
        launch();
    }
}