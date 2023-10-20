
/**
 * User info
 * The AccountNumber class manages user accounts, including registration, authentication, and retrieval.
 * It uses a file-based storage system to save and retrieve user data.
 * This class is an integral part of the Model, responsible for user account data.
 * It is linked to the User class for user data representation.
 */
import java.io.*;
import java.net.InetAddress;

public class AccountNumber {
	/**
     * Registers a new user by storing their account (1/0).
     * @param account User's account name
     * @param password User's password
     * @param firstName User's first name
     * @param lastName User's last name
     * @return 1 if registration is successful, 0 if the account already exists
     * @throws IOException if there is an issue with file operations
     */
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
            output.writeUTF("false");
            InetAddress ip = InetAddress.getLocalHost();
            output.writeUTF(ip.getHostAddress());
            output.writeUTF("8080");
            i = 1;
            output.close();
        }
        return i;
    }
    
    /**
     * Verify user credentials by checking if an account does not exist (e.g., for sign-in).
     * @param account User's account name
     * @return 1 if the account does not exist, 0 if the account already exists
     */
    public static int testing(String account) {
        int i;
        File file = new File(account);
        if (!file.exists()) {
            i = 1;
        } else i = 0;
        return i;
    }

    /**
     * Verify user credentials by checking if an account and password match (e.g., for registration).
     * @param account User's account name
     * @param password User's password
     * @return 1 if the account and password match, 0 if they do not match or the account does not exist
     * @throws IOException if there is an issue with file operations
     */
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

    /**
     * Modifies user information (e.g., password, first name, last name, VIP status) for an existing account.
     * @param account User's account name
     * @param password User's new password
     * @param firstName User's new first name
     * @param lastName User's new last name
     * @param isVip User's VIP status (true or false)
     * @return 1 if the modification is successful, 0 if the account does not exist
     * @throws IOException if there is an issue with file operations
     */
    public static int edit(String account, String password, String firstName, String lastName, String isVip) throws IOException {
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
            output.writeUTF(isVip);
            InetAddress ip = InetAddress.getLocalHost();
            output.writeUTF(ip.getHostAddress());
            output.writeUTF("8080");
            i = 1;
            output.close();
        }
        return i;
    }

    /**
     * Retrieves user information from a file.
     * @param account User's account name
     * @return User object containing the user's information
     * @throws IOException if there is an issue with file operations
     */
    public static User getUser(String account) throws IOException {
        File file = new File(account);
        DataInputStream input = new DataInputStream(new FileInputStream(file));
        User user = new User(input.readUTF(), input.readUTF(), input.readUTF(), input.readUTF());
        
     // Read VIP state and set it in the User object
        user.setVip(Boolean.valueOf(input.readUTF()));
        return user;
    }
}
