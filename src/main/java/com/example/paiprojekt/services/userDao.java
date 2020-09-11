package com.example.paiprojekt.services;

import java.util.List;

import com.example.paiprojekt.entity.User;
import org.springframework.data.repository.CrudRepository;

public interface userDao extends CrudRepository<User, Integer> {

    public User findByLogin(String login);

    @Override
    public List< User> findAll();

}
