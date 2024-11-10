package com.library.command;

import com.library.factory.LibraryServiceFactory;
import com.library.model.Book;
import com.library.service.LibraryService;
import com.library.service.UserService;
import com.library.utils.InputParserUtil;

import java.util.List;
import java.util.Optional;

import static com.library.model.Library.books;

public class SearchBookCommand implements  Command {
    @Override
    public void execute(String command, UserService userService) {
        List<String> result = InputParserUtil.parseInput(command);
        if(result.size() != 3 ) {
            System.out.println("Invalid " + result.get(0) + " command");
            return;
        }
        LibraryService libraryService = LibraryServiceFactory.getInstance(userService.getLoggedInUser());
        Optional<Book> optionalBook = libraryService.searchBook(result.get(1), result.get(2));
        if(optionalBook.isPresent()) {
            System.out.println(optionalBook.get().getTitle() + " - "+ optionalBook.get().getAuthor()+ " - Inventory:" + optionalBook.get().getBookInventory().getInventoryCount());
        } else {
            System.out.println("Searched Book not available");
        }
    }
}
