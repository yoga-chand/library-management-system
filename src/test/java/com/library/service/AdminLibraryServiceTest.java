package com.library.service;

import com.library.model.Book;
import com.library.model.Library;
import com.library.model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class AdminLibraryServiceTest {

    private AdminLibraryService adminLibraryService;
    private ByteArrayOutputStream outputStream;
    private PrintStream originalSystemOut;

    @BeforeEach
    public void setUp() {
        Library.books.clear();
        adminLibraryService = new AdminLibraryService();
        outputStream = new ByteArrayOutputStream();
        originalSystemOut = System.out;
        System.setOut(new PrintStream(outputStream));
    }

    @Test
    public void testAddBookToInventory_NewBook() {
        Book newBook = new Book("Clean Code", "Robert C. Martin", 5);
        adminLibraryService.addBookToInventory(newBook);
        assertEquals(Library.books.size(), 1);
    }

    @Test
    public void testAddBookToInventory_ExistingBook() {
        Book existingBook = new Book("Clean Code", "Robert C. Martin", 5);
        Library.books.add(existingBook);
        Book newBook = new Book("Clean Code", "Robert C. Martin", 10);
        adminLibraryService.addBookToInventory(newBook);
        assertEquals(15, Library.books.get(0).getBookInventory().getInventoryCount(),
                "The inventory count of the existing book should be updated.");
        String expectedOutput = "Book Clean Code by Robert C. Martin added successfully, inventory: 15";
        assertTrue(outputStream.toString().contains(expectedOutput), "The console output should confirm inventory update.");
    }

    @Test
    public void testDeleteBook_BookNotBorrowed() {
        Book bookToDelete = new Book("Clean Code", "Robert C. Martin", 5);
        Library.books.add(bookToDelete);
        adminLibraryService.deleteBook(bookToDelete);
        String expectedOutput = "Book \"Clean Code\" by Robert C. Martin deleted successfully.";
        assertTrue(outputStream.toString().contains(expectedOutput), "The console output should confirm book deletion.");
    }

    @Test
    public void testDeleteBook_BookBorrowed() {
        Book borrowedBook = new Book("Clean Code", "Robert C. Martin", 5);
        borrowedBook.getBookInventory().setBorrowedCount(3); // Simulate that the book is borrowed by 3 users
        Library.books.add(borrowedBook);
        adminLibraryService.deleteBook(borrowedBook);
        String expectedOutput = "Cannot delete book \"Clean Code\" because it is currently borrowed by 3 users.";
        assertTrue(outputStream.toString().contains(expectedOutput), "The console output should indicate the book is borrowed.");
    }

    @Test
    public void testBorrowBook_AdminCannotBorrow() {
        Book bookToBorrow = new Book("Clean Code", "Robert C. Martin", 5);
        User adminUser = new User("alice", "password".toCharArray(),"user");
        adminLibraryService.borrowBook(bookToBorrow, adminUser);
        String expectedOutput = "Admin cannot borrow a book";
        assertTrue(outputStream.toString().contains(expectedOutput), "The console output should indicate admin cannot borrow a book.");
    }


    @Test
    public void tearDown() {
        System.setOut(originalSystemOut);
    }
}

