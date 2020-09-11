package com.example.paiprojekt.services;

import com.example.paiprojekt.entity.Post;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface postDao extends CrudRepository<Post, Integer> {

//    public User findByLogin(String login);
//
//    @Override
//    public List< User> findAll();

    public List<Post> findAllByOrderByCreatedAtAsc();
}