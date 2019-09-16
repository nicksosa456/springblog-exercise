package com.codeup.springblog.Models;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.*;
import javax.validation.constraints.Size;

@Entity
@Table(name = "posts")
public class Post {

    @Id @GeneratedValue
    private long id;

    @Column(nullable = false, length = 100)
    @NotBlank(message = "Posts must have a title")
    @Size(min = 3, message = "A title must be at least 3 characters.")
    private String title;

    @NotBlank(message = "Posts must have a body")
    @Column(nullable = false)
    private String body;

//    @OneToOne
//    private User user;

    @ManyToOne
    @JsonManagedReference
    private User user;

    public Post() {}

    public Post(long id, String title, String body, User user) {
        this.id = id;
        this.title = title;
        this.body = body;
        this.user = user;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
