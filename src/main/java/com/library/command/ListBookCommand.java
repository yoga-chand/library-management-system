package com.library.command;

import com.library.factory.LibraryServiceFactory;
import com.library.service.LibraryService;
import com.library.service.UserService;

public class ListBookCommand implements Command {

    @Override
    public void execute(String command, UserService userService) {
        LibraryService libraryService = LibraryServiceFactory.getInstance(userService.getLoggedInUser());
        libraryService.listBooks();
    }
}
