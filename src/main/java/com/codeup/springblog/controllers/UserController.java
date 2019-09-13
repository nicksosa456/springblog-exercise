package com.codeup.springblog.controllers;

import com.codeup.springblog.Models.User;
import com.codeup.springblog.repos.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Controller
public class UserController {

    private final UserRepository userDao;
    private PasswordEncoder passwordEncoder;

    public UserController(UserRepository userDao, PasswordEncoder passwordEncoder) {
        this.userDao = userDao;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping("/register")
    public String viewRegister(Model model) {
        model.addAttribute("user", new User());
        return "users/register";
    }

    @PostMapping("/register")
    public String registerUser(@Valid User registerUser, Errors validation, Model model) {
        if(userDao.countAllByEmailOrUsername(registerUser.getEmail(), registerUser.getUsername()) > 0) {
            validation.rejectValue(
                    "username",
                    "user.username",
                    "Invalid Username and/or email."
            );
        }
        if (validation.hasErrors()) {
            model.addAttribute("errors", validation);
            model.addAttribute("user", registerUser);
            return "users/register";
        } else {
            String hash = passwordEncoder.encode(registerUser.getPassword());
            registerUser.setPassword(hash);
            userDao.save(registerUser);
            return "redirect:/login";

        }
    }
}
