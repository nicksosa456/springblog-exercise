package com.codeup.springblog.controllers;

import com.codeup.springblog.Models.Post;
import com.codeup.springblog.repos.PostRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@Controller
public class PostController {

    private final PostRepository postDao;

    public PostController(PostRepository postDao) {
        this.postDao = postDao;
    }

    @GetMapping("/posts")
    public String posts(Model viewModel) {
        viewModel.addAttribute("allPosts", postDao.findAll());
        return "posts/index";
    }

    @PostMapping("/posts")
    public String joinCohort(@RequestParam(name = "cohort") String cohort, Model model) {
        model.addAttribute("cohort", "Welcome to " + cohort + "!");
        return "posts/index";
    }

//    @PostMapping("/posts")
//    public String deletePost(@RequestParam(name = "delete") String delete, Model vModel) {
//        System.out.println(delete);
//        vModel.addAttribute("delete", "Hello" +delete);
//        return "posts/index";
//    }

    @GetMapping("/posts/{id}")
    public String individualPost(@PathVariable long id, Model viewModel) {
        viewModel.addAttribute("id", postDao.findOne(id));
        return "posts/show";
    }

    @GetMapping("/posts/create")
    public String viewCreatePost() {
        return "posts/create-post";
    }

    @PostMapping("/posts/create")
    @ResponseBody
    public String createPost() {
        return "Create the post, post the data";
    }
}
