package com.library.service;
import com.library.model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Optional;

public class UserServiceTest {

    private UserService userService;
    private User user1;
    private User user2;

    @BeforeEach
    public void setUp() {
        userService = new UserService();
        user1 = new User("john_doe", "password123".toCharArray(),"user");
        user2 = new User("jane_doe", "password456".toCharArray(), "user");
    }

    @Test
    public void testRegisterUser() {
        userService.registerUser(user1);
        assertTrue(userService.getUserList().contains(user1), "User should be registered.");
    }

    @Test
    public void testLogin_Successful() {
        userService.registerUser(user1);
        userService.registerUser(user2);

        Optional<User> loggedInUser = userService.login("john_doe", "password123".toCharArray());

        assertTrue(loggedInUser.isPresent(), "User should be logged in.");
        assertEquals(user1, loggedInUser.get(), "Logged in user should be john_doe.");
    }

    @Test
    public void testLogin_Failure_InvalidPassword() {
        userService.registerUser(user1);
        Optional<User> loggedInUser = userService.login("john_doe", "wrongpassword".toCharArray());

        assertFalse(loggedInUser.isPresent(), "Login should fail with incorrect password.");
    }

    @Test
    public void testLogin_Failure_InvalidUsername() {
        // Register user1
        userService.registerUser(user1);

        Optional<User> loggedInUser = userService.login("non_existent_user", "password123".toCharArray());

        assertFalse(loggedInUser.isPresent(), "Login should fail with incorrect username.");
    }

    @Test
    public void testGetLoggedInUser_AfterLogin() {
        userService.registerUser(user1);
        userService.login("john_doe", "password123".toCharArray());

        assertEquals(user1, userService.getLoggedInUser(), "Logged in user should be john_doe.");
    }

    @Test
    public void testGetLoggedInUser_NoLogin() {
        assertNull(userService.getLoggedInUser(), "No user should be logged in initially.");
    }
}
