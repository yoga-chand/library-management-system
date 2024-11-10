package com.library.service;

import com.library.model.Book;

import java.util.List;

public class MemberLibraryService extends LibraryService {


    public MemberLibraryService(List<Book> books) {
        super(books);
    }

    @Override
    public void addBook(Book book) {

    }

    @Override
    public void deleteBook(Book book) {

    }
}
