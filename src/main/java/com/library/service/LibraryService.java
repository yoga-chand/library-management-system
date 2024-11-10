package com.library.service;

import com.library.model.Book;
import com.library.model.Library;
import com.library.model.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.library.model.Library.books;

public abstract class LibraryService {

    public LibraryService() {
    }

    public void listBooks() {
        for (int i = 0; i < books.size(); i++) {
            System.out.println((i + 1) + ". " + books.get(i).getTitle() + " - " + books.get(i).getAuthor() + "- Inventory: " + books.get(i).getBookInventory().getInventoryCount());
        }
    }

    public Optional<Book> searchBook(String title, String author) {
        for(Book book : books) {
            if (book.getTitle().equalsIgnoreCase(title) && book.getAuthor().equalsIgnoreCase(author)) {
                return Optional.of(book);
            }
        }
        return Optional.empty();
    }

    public abstract void addBookToInventory(Book book);

    public abstract void deleteBook(Book book);

    public abstract void borrowBook(Book book, User user);


}
