import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

public class AccountNumberTest {

    @BeforeEach
    void setup() {
        // Clean up any test files created during previous tests
        File testFile = new File("testAccount");
        if (testFile.exists()) {
            testFile.delete();
        }
    }

    @Test
    void testRegisterNewUser() throws IOException {
        int result = AccountNumber.register("testAccount", "testPassword", "Test", "User");

        // Check if the registration is successful (1)
        assertEquals(1, result);

        // Verify that the user account file was created
        File testFile = new File("testAccount");
        assertTrue(testFile.exists());
    }

    @Test
    void testRegisterExistingUser() throws IOException {
        // Register a user
        AccountNumber.register("testAccount", "testPassword", "Test", "User");

        // Attempt to register the same user again
        int result = AccountNumber.register("testAccount", "newPassword", "New", "Name");

        // Check if the registration fails (0)
        assertEquals(0, result);
    }

    @Test
    void testUserAuthentication() throws IOException {
        // Register a user
        AccountNumber.register("testAccount", "testPassword", "Test", "User");

        // Test valid credentials
        int validResult = AccountNumber.testing("testAccount", "testPassword");

        // Test invalid credentials
        int invalidResult = AccountNumber.testing("testAccount", "wrongPassword");

        // Check if valid credentials result in 1 and invalid credentials result in 0
        assertEquals(1, validResult);
        assertEquals(0, invalidResult);
    }

    @Test
    void testUserAuthenticationNonExistentAccount() throws IOException {
        // Test a non-existent user account
        int result = AccountNumber.testing("nonExistentUser");

        // Check that the result is 1 (indicating the account does not exist)
        assertEquals(1, result);
    }

    @Test
    void testEditUserInformation() throws IOException {
        // Register a user
        AccountNumber.register("testAccount", "testPassword", "Test", "User");

        // Modify user information
        int result = AccountNumber.edit("testAccount", "newPassword", "New", "Name", "true");

        // Check if the modification is successful (1)
        assertEquals(1, result);

        // Verify that the user information is updated in the file
        User updatedUser = AccountNumber.getUser("testAccount");
        assertEquals("newPassword", updatedUser.getPassword());
        assertEquals("New", updatedUser.getFirstName());
        assertEquals("Name", updatedUser.getLastName());
        assertTrue(updatedUser.isVip());
    }

    @Test
    void testEditNonExistentUser() throws IOException {
        // Attempt to modify a non-existent user
        int result = AccountNumber.edit("nonExistentUser", "newPassword", "New", "Name", "true");

        // Check if the modification fails (0)
        assertEquals(0, result);
    }

    @Test
    void testGetUser() throws IOException {
        // Register a user
        AccountNumber.register("testAccount", "testPassword", "Test", "User");

        // Retrieve user information
        User user = AccountNumber.getUser("testAccount");

        // Verify user information
        assertEquals("testAccount", user.getUsername());
        assertEquals("testPassword", user.getPassword());
        assertEquals("Test", user.getFirstName());
        assertEquals("User", user.getLastName());
        assertFalse(user.isVip());
    }
}
