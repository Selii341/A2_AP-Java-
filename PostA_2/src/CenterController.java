/*For center scene*/

import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class CenterController {
    private TextField searchField;
    private TextField deleteField;
    private TextField numField;
    private TextField exportIDField;
    private TextField exportFileField;

    //same to the HelloApplication.currentUser
    public static Scene CenterScene;


    public CenterController(TextField text, TextField deleteField, TextField numField, TextField exportIDField, TextField exportFileField) {
        searchField = text;
        this.deleteField = deleteField;
        this.numField = numField;
        this.exportIDField = exportIDField;
        this.exportFileField = exportFileField;
    }
    public void addButtonHandler(ActionEvent event) {
        addPost();
    }
    public void searchButtonHandler(ActionEvent event) {
        HelloApplication.tableView.getItems().clear();
        if (searchField.getText().equals("")) {
            HelloApplication.tableView = createTable();
            HelloApplication.currentTab.setContent(HelloApplication.tableView);
            return;
        }
        try {
            int id = Integer.valueOf(searchField.getText());
           
            //enhance the loop for Map
            for(Post p : PostsCollection.getPostMap().values()) {
                if (p.getID() == id) {
                    HelloApplication.tableView.getItems().add(p);
                }
            }
        } catch (NumberFormatException ex) {
            Alert a = new Alert(Alert.AlertType.WARNING, "ID should be integer");
            a.setTitle("Warning");
            a.setHeaderText("Type error");
            a.show();
        }
    }

    public void editButtonHandler(ActionEvent event) {
        editProfile();
    }

    public void removeButtonHandler(ActionEvent event) {
        if (deleteField.getText().equals("")) {
            Alert a = new Alert(Alert.AlertType.WARNING, "ID should not be empty");
            a.setTitle("Warning");
            a.setHeaderText("ID error");
            a.show();
            return;
        }
        try {
            int id = Integer.valueOf(deleteField.getText());
            PostsCollection.removePost(id);
            PostsCollection.saveAllPostToFiles();
            HelloApplication.tableView = createTable();
            HelloApplication.currentTab.setContent(HelloApplication.tableView);

            ArrayList<Integer> shareMap = PostsCollection.getSharesMap();
            HelloApplication.pieChart.getData().clear();
            PieChart.Data slice1 = new PieChart.Data("shares: 0-99", shareMap.get(0));
            PieChart.Data slice2 = new PieChart.Data("shares: 100-999", shareMap.get(1));
            PieChart.Data slice3 = new PieChart.Data("shares: 1000+", shareMap.get(2));
            HelloApplication.pieChart.getData().add(slice1);
            HelloApplication.pieChart.getData().add(slice2);
            HelloApplication.pieChart.getData().add(slice3);
        } catch (NumberFormatException ex) {
            Alert a = new Alert(Alert.AlertType.WARNING, "ID should be integer");
            a.setTitle("Warning");
            a.setHeaderText("Type error");
            a.show();
        }
    }

    public void likesButtonHandler(ActionEvent event) {
        if (numField.getText().equals("")) {
            HelloApplication.tableView = createTable();
            HelloApplication.currentTab.setContent(HelloApplication.tableView);
            return;
        }
        try {
            int num = Integer.valueOf(numField.getText());
            List<Post> sortedPosts = PostsCollection.showTopNLikes();
            num = Math.min(num, sortedPosts.size());
            HelloApplication.tableView.getItems().clear();

            for(int i = 0; i < num; i++) {
                HelloApplication.tableView.getItems().add(sortedPosts.get(i));
            }
        } catch (NumberFormatException ex) {
        	//Warnning
            Alert a = new Alert(Alert.AlertType.WARNING, "Number should be integer");
            a.setTitle("Warning");
            a.setHeaderText("Type error");
            a.show();
        }
    }

    public void exportButtonHandler(ActionEvent event) {
        if (exportIDField.getText().equals("")) {
            Alert a = new Alert(Alert.AlertType.WARNING, "ID should not be empty");
            a.setTitle("Warning");
            a.setHeaderText("ID error");
            a.show();
            return;
        }
        try {
            int id = Integer.valueOf(exportIDField.getText());
            String filename = exportFileField.getText();
            File file = new File(filename + ".csv");
            if (file.exists()) {
                Alert a = new Alert(Alert.AlertType.WARNING, "File is existed");
                a.setTitle("Warning");
                a.setHeaderText("Repeat error");
                a.show();
                return;
            }

            file.createNewFile();
            BufferedWriter out = null;
            Post p = PostsCollection.getPostMap().get(id);
            if(p.equals(null)) {
                Alert a = new Alert(Alert.AlertType.WARNING, "Post is not existed");
                a.setTitle("Warning");
                a.setHeaderText("ID error");
                a.show();
                return;
            }

            SimpleDateFormat dateFormat= new SimpleDateFormat("dd/MM/YYYY HH:MM");
            out = new BufferedWriter(new OutputStreamWriter(
            		//false:overwrite,true:append
                    new FileOutputStream(file, false)));
            //title
            out.write("ID,content,author,likes,shares,date-time\n");
            String content = p.getID() + "," + p.getContent() + "," + p.getAuthor()
                    + "," + p.getLikes() + "," + p.getShares() + "," + dateFormat.format(p.getDate()) + "\n";
            out.write(content);
            
            //clean the memory and close the file
            out.flush();
            out.close();
            Alert a = new Alert(Alert.AlertType.INFORMATION, "Export succeeded");
            a.setTitle("Succeed");
            a.setHeaderText("Export succeeded");
            a.show();

        } catch (NumberFormatException ex) {
            Alert a = new Alert(Alert.AlertType.WARNING, "ID should be integer");
            a.setTitle("Warning");
            a.setHeaderText("Type error");
            a.show();
        } catch (IOException e) {
            System.out.println("IO Exception");
        }
    }

    //switching to the view of VIP
    public void upgradeButtonHandler(ActionEvent event) {
    	//CONFIRMATION--
        Alert a = new Alert(Alert.AlertType.CONFIRMATION, "Would you like to subscribe to the application for a monthly fee of $0?");
        a.setTitle("Confirmation");
        a.setHeaderText("Confirmation");
        //define a type 
        Optional<ButtonType> buttonType = a.showAndWait();
        
        //verify the type
        if (buttonType.get().getButtonData().equals(ButtonBar.ButtonData.OK_DONE)) {
        	//INFORMATION
            Alert alert = new Alert(Alert.AlertType.INFORMATION, "Please log out and log in again to access VIP functionalities.");
            alert.setTitle("Notice");
            alert.setHeaderText("Information");
            alert.show();

            saveVipToFile();
        } else {
            a.close();
        }
    }

    public void logoutButtonHandler(ActionEvent event) {
        HelloApplication.SignStage.setScene(SignController.signScene);
    }

    public void saveVipToFile() {
        try {
            AccountNumber.edit(HelloApplication.currentUser.getUsername(), HelloApplication.currentUser.getPassword(),
                    HelloApplication.currentUser.getFirstName(), HelloApplication.currentUser.getLastName(), "true");
        } catch (IOException io) {
            System.out.println("File not exist");
        }
    }

    public void addPost() {
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
                savePostToFile(p);
                HelloApplication.tableView = createTable();
                HelloApplication.currentTab.setContent(HelloApplication.tableView);

                ArrayList<Integer> shareMap = PostsCollection.getSharesMap();
                HelloApplication.pieChart.getData().clear();
                PieChart.Data slice1 = new PieChart.Data("shares: 0-99", shareMap.get(0));
                PieChart.Data slice2 = new PieChart.Data("shares: 100-999", shareMap.get(1));
                PieChart.Data slice3 = new PieChart.Data("shares: 1000+", shareMap.get(2));
                HelloApplication.pieChart.getData().add(slice1);
                HelloApplication.pieChart.getData().add(slice2);
                HelloApplication.pieChart.getData().add(slice3);

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

    private void savePostToFile(Post p) {
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

    public static TableView createTable() {
        PostsCollection.initPosts(loadFiles());
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

    private static HashMap<Integer, Post> loadFiles() {
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

    private void editProfile() {
        Label RegisterTitle = new Label("   Edit");
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
        RegisterAccountTextField.setText(HelloApplication.currentUser.getUsername());

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
        HelloApplication.SignStage.setScene(scene);

        RegisterReButton.setOnAction(e -> {
            HelloApplication.SignStage.setScene(CenterScene);
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
                AccountNumber.edit(RegisterAccountTextField.getText(), RegisterPasswordTextField.getText(),
                        RegisterFirstNameTextField.getText(), RegisterLastNameTextField.getText(), String.valueOf(HelloApplication.currentUser.isVip()));
                HelloApplication.SignStage.setScene(CenterScene);
                HelloApplication.currentUser = AccountNumber.getUser(RegisterAccountTextField.getText());
                HelloApplication.SignStage.setTitle("Welcome " + HelloApplication.currentUser.getFirstName() + " " + HelloApplication.currentUser.getLastName());
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        });
    }

}
