package com.codeup.springblog.services;

import com.codeup.springblog.Models.User;
import com.codeup.springblog.repos.UserRepository;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service("usersService")
public class UserService {
    UserRepository usersRepo;

    public UserService(UserRepository usersRepo) {
        this.usersRepo = usersRepo;
    }

    public boolean isLoggedIn() {
        boolean isAnonymousUser = SecurityContextHolder.getContext().getAuthentication() instanceof AnonymousAuthenticationToken;
        return ! isAnonymousUser;
    }

    public User loggedInUser() {
        if (! isLoggedIn()) {
            return null;
        }
        User sessionUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        return usersRepo.getOne(sessionUser.getId());
    }

    public boolean isOwner(User postUser) {
        if(isLoggedIn()) {
            return (postUser.getUsername().equals(loggedInUser().getUsername()));
        }
        return false;
    }

    public boolean canEditProfile(User profileUser) {
        return isLoggedIn() && (profileUser.getId() == loggedInUser().getId());
    }
}
