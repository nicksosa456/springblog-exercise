package com.codeup.springblog.controllers;

import com.codeup.springblog.Models.User;
import com.codeup.springblog.repos.UserRepository;
import com.codeup.springblog.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Controller
public class UserController {

    private final UserRepository userDao;
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserService userService;

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

    @GetMapping("/users/{id}")
    public String showUser(@PathVariable Long id, Model vModel) {
        User user = userDao.getOne(id);
        vModel.addAttribute("user", user);
        vModel.addAttribute("sessionUser", userService.loggedInUser());
        vModel.addAttribute("showEditControls", userService.canEditProfile(user));
        return "users/profile";
    }

    @GetMapping("/users/profile")
    public String showProfile(Model vModel) {
        User loggedUser = userService.loggedInUser();
        if (loggedUser == null) {
            vModel.addAttribute("msg", "You need to login to view someones profile");
            return "error/custom";
        }
        return "redirect:/users/"+userService.loggedInUser().getId();
    }

    @PostMapping("/users/{id}/edit")
    public String editUser(@PathVariable Long id, @Valid User editedUser, Errors validation, Model model) {
        User loggedUser = userService.loggedInUser();
        System.out.println(loggedUser.getPassword());
        editedUser.setPassword(loggedUser.getPassword());
        System.out.println(editedUser.getId());
        System.out.println(editedUser.getUsername());
        System.out.println(editedUser.getEmail());
        System.out.println(editedUser.getPassword());
        if (!passwordEncoder.matches(editedUser.getPassword(), loggedUser.getPassword())){
            return "redirect:/users/profile";
        }

        if(userDao.countAllByEmailOrUsername(editedUser.getEmail(), editedUser.getUsername()) > 0) {
            validation.rejectValue(
                    "username",
                    "user.username",
                    "Invalid Username and/or email."
            );
        }

        if (validation.hasErrors()) {
            System.out.println(validation.getAllErrors());
            model.addAttribute("errors", validation);
            model.addAttribute("user", editedUser);
            return "redirect:/users/profile";
        }
        userDao.save(editedUser);
        return "redirect:/users/"+id;
    }
}
