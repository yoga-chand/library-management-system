package com.library.service;

import com.library.model.Book;
import com.library.model.Library;
import com.library.model.User;

import java.util.Optional;

public class AdminLibraryService extends LibraryService {


    public AdminLibraryService() {
        super();
    }

    @Override
    public void addBookToInventory(Book book) {
        Optional<Book> optionalBook = searchBook(book.getTitle(), book.getAuthor());
        if(optionalBook.isPresent()) {
            Book existingBook = optionalBook.get();
            existingBook.getBookInventory().updateInventory(book.getBookInventory().getInventoryCount());
            System.out.println("Book "+ existingBook.getTitle() + " by "+ existingBook.getAuthor()+ " added successfully," + " inventory: "+existingBook.getBookInventory().getInventoryCount());
        } else {
            Library.books.add(book);
            System.out.println("Book "+ book.getTitle() + " by "+ book.getAuthor()+ " added successfully," + " inventory: "+book.getBookInventory().getInventoryCount());
        }


    }

    @Override
    public void deleteBook(Book book) {
        Optional<Book> optionalBook = searchBook(book.getTitle(), book.getAuthor());
        if(optionalBook.isPresent()) {
            Book existingBook = optionalBook.get();
            if (existingBook.getBookInventory().getBorrowedCount() > 0) {
                System.out.println("Cannot delete book \"" + existingBook.getTitle() + "\" because it is currently borrowed by " + existingBook.getBookInventory().getBorrowedCount() + " users.");
            } else {
                Library.books.remove(book);
                System.out.println("Book \"" + book.getTitle() + "\" by " + book.getAuthor() + " deleted successfully.");
            }
        }

    }

    @Override
    public void borrowBook(Book book, User user) {
        System.out.println("Admin cannot borrow a book");
    }
}
