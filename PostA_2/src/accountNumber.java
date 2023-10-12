/**User info
 * This class appears to be related to managing user accounts 
 * (interact with the User class and, indirectly, the Post class, for handling user data)
 * contains methods for user registration, verification, editing user information, and retrieving user data.
 * acts as part of the Controller in MVC, managing user interactions and updating the Model and View accordingly.
 */






import java.io.*;
import java.net.InetAddress;
public class accountNumber {
	//Registers a new user by storing their account （1/0）
    public static int register(String account, String password, String firstName, String lastName) throws IOException {
        int i;
        File file = new File(account);
        if (file.exists()) {
            i = 0;
        } else {
            DataOutputStream output = new DataOutputStream(new FileOutputStream(file, true));
            output.writeUTF(account);
            output.writeUTF(password);
            output.writeUTF(firstName);
            output.writeUTF(lastName);
            InetAddress ip = InetAddress.getLocalHost();
            output.writeUTF(ip.getHostAddress());
            output.writeUTF("8080");
            i = 1;
            output.close();
        }
        return i;
    }
    
    //Verify user credentials by checking if an account not exists.eg. signin（1/0）
    public static int testing(String account) {
        int i;
        File file = new File(account);
        if (!file.exists()) {
            i = 1;
        } else i = 0;
        return i;
    }
    //Verify user credentials by checking if an account and password match.eg.register
    public static int testing(String account, String password) throws IOException {
        int i;
        File file = new File(account);
        if (!file.exists()) {
            i = 0;
        } else {
            DataInputStream input = new DataInputStream(new FileInputStream(file));
            if (account.equals(input.readUTF()) && password.equals(input.readUTF())) {
                i = 1;
            } else i = 0;
            input.close();
        }
        return i;
    }
    
    //Modifies user information (e.g.sss) for an existing account.（1/0）
    public static int Edit(String account, String password, String firstName, String lastName) throws IOException {
        int i;
        File file = new File(account);
        if (!file.exists()) {
            i = 0;
        } else {
            DataOutputStream output = new DataOutputStream(new FileOutputStream(file, false));
            output.writeUTF(account);
            output.writeUTF(password);
            output.writeUTF(firstName);
            output.writeUTF(lastName);
            InetAddress ip = InetAddress.getLocalHost();
            output.writeUTF(ip.getHostAddress());
            output.writeUTF("8080");
            i = 1;
            output.close();
        }
        return i;
    }

    //Retrieves user information from a file.（1/0）
    public static User getUser(String account) throws IOException {
        File file = new File(account);
        DataInputStream input = new DataInputStream(new FileInputStream(file));
        User user = new User(input.readUTF(), input.readUTF(), input.readUTF(), input.readUTF());
        return user;
    }
}
