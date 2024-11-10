package com.library.model;

import java.util.ArrayList;
import java.util.List;

public class User {

    private String userName;

    private char[] password;

    private String role;

    private List<Book> borrowedBooks;

    public User(String userName, char[] password,  String role) {
        this.userName = userName;
        this.password = password;
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