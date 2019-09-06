package com.codeup.springblog.controllers;

import com.codeup.springblog.Models.Post;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class PostController {

    @GetMapping("/posts")
    public String posts() {
        return "posts/index";
    }

    @GetMapping("/posts/{id}")
    public String individualPost(@PathVariable int id, Model viewModel) {
        Post post = new Post("Test Post", "First test post");
        viewModel.addAttribute("id", post);
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
