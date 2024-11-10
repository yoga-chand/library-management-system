package com.library.service;

import com.library.model.Book;
import com.library.model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.List;

public class MemberLibraryServiceTest {

    private MemberLibraryService memberLibraryService;
    private Book book;
    private User user;
    private ByteArrayOutputStream outputStream;
    private PrintStream originalSystemOut;

    @BeforeEach
    public void setUp() {
        memberLibraryService = new MemberLibraryService();
        book = new Book("Clean Code", "Robert C. Martin", 10);
        user = new User("john_doe", "password".toCharArray(),"user");
        outputStream = new ByteArrayOutputStream();
        originalSystemOut = System.out;
        System.setOut(new PrintStream(outputStream));
    }

    @Test
    public void testAddBookToInventory() {
        memberLibraryService.addBookToInventory(book);
        String expectedMessage = "Member cannot add a book!";
        assertTrue(outputStream.toString().contains(expectedMessage),
                "The console output should indicate that members cannot add books.");
    }

    @Test
    public void testDeleteBook() {
        memberLibraryService.deleteBook(book);
        String expectedMessage = "Member cannot delete a book!";
        assertTrue(outputStream.toString().contains(expectedMessage),
                "The console output should indicate that members cannot delete books.");
    }

    @Test
    public void testBorrowBook_SuccessfulBorrow() {
        assertTrue(book.getBookInventory().hasEnoughCopiesToBorrow(), "The book should have enough copies to borrow.");
        memberLibraryService.borrowBook(book, user);
        assertEquals(1, book.getBookInventory().getBorrowedCount(),
                "The borrowed count should increase by 1 after borrowing.");
        List<Book> borrowedBooks = user.getBorrowedBooks();
        assertTrue(borrowedBooks.contains(book), "The book should be in the user's borrowed books list.");
    }

    @Test
    public void testBorrowBook_NotEnoughCopies() {
        book.getBookInventory().setInventoryCount(0);
        book.getBookInventory().setBorrowedCount(0);
        memberLibraryService.borrowBook(book, user);
        assertEquals(0, book.getBookInventory().getBorrowedCount(),
                "The borrowed count should remain 0 if there are no copies to borrow.");
        List<Book> borrowedBooks = user.getBorrowedBooks();
        assertFalse(borrowedBooks.contains(book), "The book should not be in the user's borrowed books list if no copies are available.");
    }

    @Test
    public void testBorrowBook_BookAlreadyBorrowed() {
        book.getBookInventory().setBorrowedCount(1);
        memberLibraryService.borrowBook(book, user);
        assertEquals(2, book.getBookInventory().getBorrowedCount(),
                "The borrowed count should increase by 1 after borrowing.");
        List<Book> borrowedBooks = user.getBorrowedBooks();
        assertTrue(borrowedBooks.contains(book), "The book should be in the user's borrowed books list.");
    }

    @Test
    public void tearDown() {
        System.setOut(originalSystemOut);
    }
}

