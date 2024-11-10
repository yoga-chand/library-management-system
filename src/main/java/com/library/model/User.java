package com.library.model;

public class User {

    private String userName;

    private char[] password;

    private String name;

    private String role;

    public User(String userName, char[] password, String name, String role) {
        this.userName = userName;
        this.password = password;
        this.name = name;
        this.role = role;
    }


}