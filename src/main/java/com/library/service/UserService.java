package com.library.service;

import com.library.model.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UserService {

    private List<User> userList;

    public UserService() {
        this.userList = new ArrayList<>();
    }

    public void addUser(User user) {
        userList.add(user);
    }

    public Optional<User> login(String userName, char[] password) {
        for(User user : userList) {

        }
        return Optional.empty();
    }
}
