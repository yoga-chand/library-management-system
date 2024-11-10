package com.library;
import com.library.command.*;
import com.library.service.UserService;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class LibraryManagementSystem {

    private static Map<String, Command> commandMap = new HashMap<>();

    static {
        LoginCommand loginCommand = new LoginCommand();
        RegisterCommand registerCommand = new RegisterCommand();
        AddBookCommand addBookCommand = new AddBookCommand();
        ReturnBookCommand returnBookCommand = new ReturnBookCommand();
        ListBookCommand listBookCommand = new ListBookCommand();
        DeleteBookCommand deleteBookCommand = new DeleteBookCommand();
        SearchBookCommand searchBookCommand = new SearchBookCommand();
        BorrowBookCommand borrowBookCommand = new BorrowBookCommand();
        commandMap.put("register", registerCommand);
        commandMap.put("login", loginCommand);
        commandMap.put("add", addBookCommand);
        commandMap.put("list", listBookCommand);
        commandMap.put("delete", deleteBookCommand);
        commandMap.put("return", returnBookCommand);
        commandMap.put("search", searchBookCommand);
        commandMap.put("borrow", borrowBookCommand);

    }

    public static void main(String[] args) {

        UserService userService = new UserService();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\nWelcome To Library Management System");
            String action = scanner.nextLine().trim().toLowerCase();
            if(action.isEmpty()){
                System.out.println("Invalid command");
                return;
            }
            String[] commands = action.split("\\s+");
            if(commands.length > 0) {
                commandMap.get(commands[0]).execute(action, userService);
            } else {
                System.out.println("Invalid command");
                break;
            }
        }
    }

}
