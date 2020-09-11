package com.example.paiprojekt.entity;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer postId;

    private Date updatedAt;

    private Date createdAt;

    private String content;

    @ManyToOne
    @JoinColumn(name = "userid")
    private User user;

    public Post(String content) {
        this.content = content;
    }

    public Post() { }

    @PrePersist
    void createdAt() {
        if(this.createdAt == null){
            this.createdAt = new Date();
        }
    }

    @PreUpdate
    private void updatedAt() {
        this.updatedAt = new Date();
    }

    public Integer getPostId() {
        return postId;
    }

    public void setPostId(Integer postId) {
        this.postId = postId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
