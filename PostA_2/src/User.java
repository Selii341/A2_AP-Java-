
/*
 * The User class represents user data and is a core part of the Model in the MVC pattern.(attributes and methods are defined)
 * It encapsulates information such as username, password, first name, last name, and VIP status.
 * User data is used for user authentication, registration, and account management.
 */


public class User {
    private String username;

    private String password;
    private String firstName;
    private String lastName;

    private boolean isVip = false;
    
 // Constructor to initialize a user with basic information
    public User(String username, String password, String firstName, String lastName) {
        this.username = username;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
     // By default, a user is not a VIP
        isVip = false;
    }

 // Getter and setter
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isVip() {
        return isVip;
    }
    
    //upgrade function
    public void setVip(boolean vip) {
        isVip = vip;
    }
}
