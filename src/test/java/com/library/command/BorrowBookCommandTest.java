package com.library.command;
import com.library.model.Book;
import com.library.model.User;
import com.library.service.LibraryService;
import com.library.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class BorrowBookCommandTest {

    private BorrowBookCommand borrowBookCommand;
    private UserService userService;
    private LibraryService libraryService;
    private User loggedInUser;
    private ByteArrayOutputStream outputStream;
    private PrintStream originalSystemOut;

    @BeforeEach
    public void setUp() {
        borrowBookCommand = new BorrowBookCommand();
        userService = mock(UserService.class);
        libraryService = mock(LibraryService.class);
        loggedInUser = new User("john_doe", "password123".toCharArray(),"user");
        when(userService.getLoggedInUser()).thenReturn(loggedInUser);
        outputStream = new ByteArrayOutputStream();
        originalSystemOut = System.out;
        System.setOut(new PrintStream(outputStream));
    }

    @Test
    public void testExecute_InvalidCommandFormat() {
        String command = "borrow";

        borrowBookCommand.execute(command, userService);

        String expectedMessage = "Invalid borrow command";
        assertTrue(outputStream.toString().contains(expectedMessage),
                "The console output should indicate that the command is invalid.");
    }

    @Test
    public void testExecute_BookNotAvailable() {
        when(libraryService.searchBook("Clean Code", "Robert C. Martin")).thenReturn(Optional.empty());

        String command = "borrow \"Clean Code\" \"Robert C. Martin\"";

        borrowBookCommand.execute(command, userService);

        String expectedMessage = "Book \"Clean Code\" not available.";
        assertTrue(outputStream.toString().contains(expectedMessage),
                "The console output should indicate that the book is not available.");
        verify(libraryService, never()).borrowBook(any(), any());
    }

    @Test
    public void tearDown() {
        System.setOut(originalSystemOut);
    }
}
