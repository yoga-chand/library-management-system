package com.library.command;

import com.library.model.Admin;
import com.library.model.Member;
import com.library.model.User;
import com.library.service.LibraryService;
import com.library.service.UserService;

public class RegisterCommand implements Command {

    @Override
    public void execute(String command, UserService userService) {

        String[] commands = command.split(" ");
        if(commands.length != 4) {
            System.out.println("Invalid " + commands[0] + " command");
        }
        if (commands[1].equalsIgnoreCase("admin")) {
            User admin = new Admin(commands[2], commands[3].toCharArray(), commands[1]);
            userService.registerUser(admin);
            System.out.println("Admin " + commands[2] + " successfully registered.");
        } else if(commands[1].equalsIgnoreCase("user")) {
            User user = new Member(commands[2], commands[3].toCharArray(), commands[1]);
            userService.registerUser(user);
            System.out.println("User " + commands[2] + " successfully registered.");
        }
    }
}
