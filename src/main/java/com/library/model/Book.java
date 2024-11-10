package com.library.model;

public class Book {
    private String title;
    private String author;

    private BookInventory bookInventory;

    public Book(String title, String author) {
        this.title = title;
        this.author = author;
    }

    public Book(String title, String author, int inventory) {
        this.title = title;
        this.author = author;
        this.bookInventory = new BookInventory(inventory);
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public BookInventory getBookInventory() {
        return bookInventory;
    }
}
