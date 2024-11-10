package com.library.factory;

import com.library.model.User;
import com.library.service.AdminLibraryService;
import com.library.service.LibraryService;
import com.library.service.MemberLibraryService;

public class LibraryServiceFactory {

    public static LibraryService getInstance(User user) {
        if(user.getRole().equalsIgnoreCase("admin")) {
            return new AdminLibraryService();
        } else if(user.getRole().equalsIgnoreCase("user")) {
            return new MemberLibraryService();
        } else {
            throw new RuntimeException("Invalid Role");
        }
    }
}
