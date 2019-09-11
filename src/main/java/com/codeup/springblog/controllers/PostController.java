package com.codeup.springblog.controllers;

import com.codeup.springblog.Models.Post;
import com.codeup.springblog.Models.User;
import com.codeup.springblog.repos.PostRepository;
import com.codeup.springblog.repos.UserRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class PostController {

    private final PostRepository postDao;
    private final UserRepository userDao;

    public PostController(PostRepository postDao, UserRepository userDao) {
        this.userDao = userDao;
        this.postDao = postDao;
    }

    @GetMapping("/posts")
    public String posts(Model viewModel) {
        viewModel.addAttribute("allPosts", postDao.findAll());
        return "posts/index";
    }

    @GetMapping("/posts/{id}")
    public String individualPost(@PathVariable long id, Model viewModel) {
        viewModel.addAttribute("post", postDao.findOne(id));
        return "posts/show";
    }

    @PostMapping("/posts/{id}/delete")
    public String deletePost(@PathVariable long id, Model vModel) {
        postDao.delete(id);
        return "redirect:/posts";
    }

    @PostMapping("/posts/{id}/edit")
    public String editPost(@PathVariable long id, @ModelAttribute Post editPost
//                           @RequestParam(name = "title") String title, @RequestParam(name = "body") String body
    ) {
        Post postToEdit = postDao.findOne(id);
//        editPost.setTitle(title);
//        editPost.setBody(body);
        postDao.save(editPost);
        return "redirect:/posts/{id}";
    }

    @GetMapping("/posts/create")
    public String viewCreatePost(Model model) {
        model.addAttribute("post", new Post());
        return "posts/createPost";
    }

    @PostMapping("/posts/create")
    public String createPost(@ModelAttribute Post postToBeCreated) {
        User loggedUser = userDao.findOne(1L);
        postToBeCreated.setUser(loggedUser);

        Post createdPost = postDao.save(postToBeCreated);
        return "redirect:/posts/"+createdPost.getId();
    }
}
