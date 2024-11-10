package com.library.service;

import com.library.model.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UserService {

    private List<User> userList;

    private User loggedInUser;

    public UserService() {
        this.userList = new ArrayList<>();
    }

    public void registerUser(User user) {
        userList.add(user);
    }

    public Optional<User> login(String userName, char[] password) {
        for(User user : userList) {
            if(user.getUserName().equalsIgnoreCase(userName) && new String(user.getPassword()).equals(new String(password))) {
                loggedInUser = user;
                return Optional.of(user);
            }
        }
        return Optional.empty();
    }

    public User getLoggedInUser() {
        return loggedInUser;
    }
}
