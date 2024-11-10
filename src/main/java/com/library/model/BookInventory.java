package com.library.model;

public class BookInventory {

    private Book book;
    private int inventory;
    private int borrowedCount;

    public BookInventory(Book book, int inventory, int borrowedCount) {
        this.book = book;
        this.inventory = inventory;
        this.borrowedCount = borrowedCount;
    }
}
