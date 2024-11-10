package com.library.service;

import com.library.model.Book;
import com.library.model.User;

import java.util.List;

public class MemberLibraryService extends LibraryService {


    public MemberLibraryService() {
        super();
    }

    @Override
    public void addBookToInventory(Book book) {
        System.out.println("Member cannot add a book!");
    }

    @Override
    public void deleteBook(Book book) {
        System.out.println("Member cannot delete a book!");
    }

    @Override
    public void borrowBook(Book book, User user) {
        if(book.getBookInventory().hasEnoughCopiesToBorrow()) {
            book.getBookInventory().setBorrowedCount(book.getBookInventory().getBorrowedCount() + 1);
            user.getBorrowedBooks().add(book);
        }
    }
}
