package com.codeup.springblog.controllers;

import com.codeup.springblog.Models.User;
import com.codeup.springblog.repos.UserRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class UserController {

    private final UserRepository userDao;

    public UserController(UserRepository userDao) {
        this.userDao = userDao;
    }

    @GetMapping("/register")
    public String viewRegister() {
        return "users/register";
    }

    @PostMapping("/register")
    public String registerUser(
            @RequestParam(name = "email") String email,
            @RequestParam(name = "username") String username,
            @RequestParam(name = "password") String password
    ) {
        User registerUser = new User();
        registerUser.setEmail(email);
        registerUser.setUsername(username);
        registerUser.setPassword(password);
        userDao.save(registerUser);
        return "redirect:/posts";
    }
}
