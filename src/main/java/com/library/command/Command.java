package com.library.command;

import com.library.service.LibraryService;
import com.library.service.UserService;

public interface Command {

    void execute(String command, UserService userService);
}
