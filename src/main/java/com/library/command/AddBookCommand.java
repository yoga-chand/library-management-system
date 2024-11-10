package com.library.command;

import com.library.factory.LibraryServiceFactory;
import com.library.model.Book;
import com.library.service.AdminLibraryService;
import com.library.service.LibraryService;
import com.library.service.UserService;
import com.library.utils.InputParserUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AddBookCommand implements Command {

    @Override
    public void execute(String command, UserService userService) {

        List<String> result = InputParserUtil.parseInput(command);
        if(result.size() != 4 ) {
            System.out.println("Invalid " + result.get(0) + " command");
            return;
        }
        int inventory = 0;
        try {
            inventory = Integer.parseInt(result.get(3));
            Book book = new Book(result.get(1), result.get(2), inventory);
            LibraryService libraryService = LibraryServiceFactory.getInstance(userService.getLoggedInUser());
            libraryService.addBookToInventory(book);
        } catch(Exception e) {
            System.out.println("Invalid " + result.get(0) + " command");
        }

    }
}
