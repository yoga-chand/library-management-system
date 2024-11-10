package com.library.command;

import com.library.model.Admin;
import com.library.model.Member;
import com.library.model.User;
import com.library.service.LibraryService;
import com.library.service.UserService;

import java.util.Optional;

public class LoginCommand implements  Command {
    @Override
    public void execute(String command, UserService userService) {
        String[] commands = command.split(" ");
        if(commands.length != 3) {
            System.out.println("Invalid " + commands[0] + " command");
        }
        Optional<User> optionalUser = userService.login(commands[1], commands[2].toCharArray());
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            System.out.println(user.getClass().getSimpleName() + " " + user.getUserName() + " successfully logged in.");
        } else {
            System.out.println("Invalid credentials.");
        }
    }
}
