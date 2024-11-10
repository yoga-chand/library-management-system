package com.library.command;

import com.library.factory.LibraryServiceFactory;
import com.library.model.Book;
import com.library.service.AdminLibraryService;
import com.library.service.LibraryService;
import com.library.service.UserService;
import com.library.utils.InputParserUtil;

import java.util.List;

public class DeleteBookCommand implements  Command {

    @Override
    public void execute(String command, UserService userService) {
        List<String> result = InputParserUtil.parseInput(command);
        if(result.size() != 3 ) {
            System.out.println("Invalid " + result.get(0) + " command");
            return;
        }
        Book book = new Book(result.get(1), result.get(2));
        LibraryService libraryService = LibraryServiceFactory.getInstance(userService.getLoggedInUser());
        libraryService.deleteBook(book);
    }
}
