package com.library.command;

import com.library.model.User;
import com.library.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

public class AddBookCommandTest {

    @Mock
    private UserService userService; // Mock UserService
    @InjectMocks
    private AddBookCommand addBookCommand; // Command being tested

    private ByteArrayOutputStream outputStream;
    private PrintStream originalSystemOut;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        outputStream = new ByteArrayOutputStream();
        originalSystemOut = System.out;
        System.setOut(new PrintStream(outputStream));
    }

    @Test
    public void testExecute_ValidCommand() {
        String command = "add \"Clean Code\" \"Robert C. Martin\" 5";
        User user = new User("test", "test123".toCharArray(), "admin");
        when(userService.getLoggedInUser()).thenReturn(user);
        addBookCommand.execute(command, userService);

    }

    @Test
    public void testExecute_InvalidCommand() {
        String command = "add \"Clean Code\"";
        addBookCommand.execute(command, userService);
        String expectedOutput = "Invalid add command";
        assertTrue(outputStream.toString().contains(expectedOutput));
    }

    @Test
    public void testExecute_InvalidInventory() {
        String command = "add \"Clean Code\" \"Robert C. Martin\" abc";
        addBookCommand.execute(command, userService);
        String expectedOutput = "Invalid add command";
        assertTrue(outputStream.toString().contains(expectedOutput));

    }

    @Test
    public void testExecute_EmptyCommand() {
        String command = "add";
        addBookCommand.execute(command, userService);
        String expectedOutput = "Invalid add command";
        assertTrue(outputStream.toString().contains(expectedOutput));

    }
}
