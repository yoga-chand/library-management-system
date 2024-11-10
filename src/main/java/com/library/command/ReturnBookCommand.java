package com.library.command;

import com.library.factory.LibraryServiceFactory;
import com.library.model.Book;
import com.library.service.LibraryService;
import com.library.service.UserService;
import com.library.utils.InputParserUtil;

import java.util.List;
import java.util.Optional;

public class ReturnBookCommand implements Command {
    @Override
    public void execute(String command, UserService userService) {
        List<String> commands = InputParserUtil.parseInput(command);
        if(commands.size() != 3 ) {
            System.out.println("Invalid " + commands.get(0) + " command");
            return;
        }
        LibraryService libraryService = LibraryServiceFactory.getInstance(userService.getLoggedInUser());
        Optional<Book> optionalBook = libraryService.searchBook(commands.get(1), commands.get(2));
        if (optionalBook.isPresent() && optionalBook.get().getBookInventory().isReturnValid()) {
            optionalBook.get().getBookInventory().updateInventoryDuringReturn();
            System.out.println("Book \"" + commands.get(1) + "\" successfully returned.");
        } else {
            System.out.println("You haven't borrowed the book \"" + commands.get(1) + "\".");
        }
    }
}
