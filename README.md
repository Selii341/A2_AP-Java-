# A2_AP
Assignment 2 for Advanced Programming
README

1. Program Introduction
The Data Analytics Hub is a GUI application developed in Java SE 8 using JavaFX. It allows multiple users to create profiles, login, and manage collections of social media posts for analysis. Users can perform various actions on their posts, including editing their profiles, adding, retrieving, and removing posts, as well as exporting posts to CSV files. In addition, It has developed a personalized service, i.e. divided into regular and VIP user rights and benefits

IDE Used: I recommend using Eclipse IDE with Java Development Kit (JDK) 11 or later for this project. This project utilizes JavaFX for the graphical user interface (GUI).

Java Version: This project is built with Java SE 8, which includes JavaFX 21(LTS) as a standard library. Make sure to have at least Java 8 installed on your system.

Database: This project does not use a database. User data and posts are stored in memory using data structures and text files.
2. How to Install and Run the Code
Clone the Repository: Begin by cloning this repository to your local machine. [See 5.1]You can use a Git client or download the repository as a ZIP archive and extract it.
Open in Eclipse IDE:
Open Eclipse IDE (make sure you have Java 11 or later installed).
Click on File -> Open Projects from File System.
Select the root directory where you cloned the repository and click Finish.
Configure JavaFX:
In Eclipse, right-click on your project in the Project Explorer.
Select Build Path -> Configure Build Path.
In the Libraries tab, ensure that the JavaFX SDK is included. If not, click Add Library and select JavaFX SDK.
Run the Application:
Locate the HelloApplication class (inside the src folder) and right-click on it.
Select Run As -> Java Application.
The application window should open.
Using the Application:
The application provides a login screen where you can sign in or register a new account.
After signing in, you'll have access to the main content view, where you can interact with social media posts.

3. Functionalities
The program is divided into Basic Functionalities and VIP Functionalities:
3.1 Basic Functionalities
User Sign-in and Login: Users can create profiles with a unique username, password (unencrypted), first name, and last name. Then, log in using their username and password.
User Dashboard: After login, users are greeted with a dashboard displaying a welcome message containing their full name on the title.It contains the post and profile management.
Management: Users can manage their collection of social media posts and personal profiles from the dashboard. This includes:
Editing their profile (changing first name, last name, username, and password).
Optional VIP benefits can be purchased, must log in again to load benefits.
Users can log out of the application.
Adding social media posts to the collection with details like ID, content, author, #likes, #shares, and date-time.
Retrieving posts based on their ID.
Removing posts based on their ID.
Retrieving the top N posts with the most likes for a given user or across all users.
Exporting posts to CSV files.

3.2 VIP Benefits
Advanced Management System: 2 new features have been added to the existing ones 
Pie Chart Summuarisation: VIP users can view a pie chart illustrating the distribution of shares in posts. Posts are categorized into three categories: 0-99, 100-999, and 1000 or more.
Bulk Import: VIP users can import multiple social media posts from a CSV file. 

4. OO Design
The program is designed using object-oriented principles by following the Model-View-Controller (MVC) pattern to organize and manage the various components. The design aims to promote code reusability, encapsulation, and maintainability. Below are the key classes and their roles in the program.
4.1 Class Structure
User Management Classes(Model):
User: Represents a user profile, encapsulating user-specific information such as username, password, first name, last name, and VIP status. It is composed within AccountNumber, as each User is associated with a unique AccountNumber.It has a composition relationship with Post, as each User has a collection of social media posts and Interacts with Post to manage the user's social media posts.

AccountNumber: Manages user accounts, and provides methods for user authentication, data retrieval, and user profile management. It has a composition relationship with the User, as each AccountNumber is associated with a unique User and Interacts with the User to perform user authentication and data retrieval.

Social Media Post Classes(Model):
Post: Represents social media posts, encapsulating details such as content, author, likes, shares, date-time, etc. It is composed within User, as each User has a collection of social media posts. Also, aggregated within PostsCollection, which manages a collection of posts.

PostsCollection: Manages the collection of social media posts and offers various methods for post creation, retrieval, and removal. It has an aggregation relationship with Post, as it stores and manages multiple Post objects and Interacts with CenterController to provide access to social media posts for display.

Controller Classes(Controller):
CenterController: Controls the main content view and serves as an intermediary between the user interface and the underlying application logic. It Interacts with SignController to navigate users to the dashboard after a successful login and utilizes PostsCollection to display and navigate posts in the user's dashboard.

SignController: Manages the user login process, including authentication and navigation to registration screens. It interacts with CenterController to navigate users to the dashboard upon successful login and Collaborates with AccountNumber for user authentication. Which has a "uses" relationship with RegisterController, as both classes handle user account-related functionality.

RegisterController: Manages user registration provides a registration form and sends the feedback. It collaborates with AccountNumber to create new user accounts, which has a "uses" relationship with SignController, as both classes deal with user account-related functionality.

View (GUI): The application's graphical user interface is built with JavaFX and is represented by the HelloApplication class. This class handles the UI elements and scene transitions that manage the user interface and different scenes, including the login and registration screens.

AccountNumberTest and PostsCollectionTest (JUnit Test Classes): These test classes focus on non-GUI functions and ensure the correctness of the core application functionality.
AccountNumberTest tests the functionality of AccountNumber for user authentication and data retrieval.
PostsCollectionTest tests the functionality of PostsCollection for managing social media posts.



5. Resources
5.1 Github
https://github.com/Selii341/A2_AP.git 

5.2 Reference of environment variables setting
https://www.azul.com/downloads/?os=macos&package=jdk-fx#zulu 

