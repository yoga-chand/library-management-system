package com.library.model;

import java.util.ArrayList;
import java.util.List;

public class User {

    private String userName;

    private char[] password;

    private String name;

    private String role;

    private List<Book> borrowedBooks;

    public User(String userName, char[] password, String name, String role) {
        this.userName = userName;
        this.password = password;
        this.name = name;
        this.role = role;
        this.borrowedBooks = new ArrayList<>();
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public char[] getPassword() {
        return password;
    }

    public void setPassword(char[] password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public List<Book> getBorrowedBooks() {
        return borrowedBooks;
    }
}