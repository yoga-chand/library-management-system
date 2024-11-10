package com.library.command;

import com.library.factory.LibraryServiceFactory;
import com.library.model.Book;
import com.library.service.LibraryService;
import com.library.service.UserService;
import com.library.utils.InputParserUtil;

import java.util.List;
import java.util.Optional;

public class BorrowBookCommand implements  Command {
    @Override
    public void execute(String command, UserService userService) {
        List<String> result = InputParserUtil.parseInput(command);
        if(result.size() != 3 ) {
            System.out.println("Invalid " + result.get(0) + " command");
            return;
        }
        LibraryService libraryService = LibraryServiceFactory.getInstance(userService.getLoggedInUser());
        Optional<Book> optionalBook = libraryService.searchBook(result.get(1), result.get(2));
        if (optionalBook.isPresent() && optionalBook.get().getBookInventory().hasEnoughCopiesToBorrow()) {
            libraryService.borrowBook(optionalBook.get(), userService.getLoggedInUser());
            System.out.println("Book \"" + result.get(1) + "\" successfully borrowed.");
        } else {
            System.out.println("Book \"" + result.get(1) + "\" not available.");
        }
    }
}
