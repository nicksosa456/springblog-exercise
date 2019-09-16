package com.codeup.springblog.controllers;

import com.codeup.springblog.Models.Post;
import com.codeup.springblog.repos.PostRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class viewAllPostsInJSONFormat {
    private final PostRepository postDao;

    public viewAllPostsInJSONFormat(PostRepository postDao) {
        this.postDao = postDao;
    }

    @GetMapping("/posts.json")
    public @ResponseBody List<Post> viewAllPostsInJSONFormat() {
        return (List<Post>) postDao.findAll();
    }

    @GetMapping("/posts/ajax")
    public String viewAllPostsWithAjax() {
        return "posts/ajax";
    }
}
