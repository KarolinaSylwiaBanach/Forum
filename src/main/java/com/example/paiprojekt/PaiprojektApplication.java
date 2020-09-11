package com.example.paiprojekt;

import com.example.paiprojekt.services.userDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.annotation.PostConstruct;

@SpringBootApplication
public class PaiprojektApplication {

    @Autowired
    private userDao dao;
    @Autowired
    private PasswordEncoder passwordEncoder;

    public static void main(String[] args) {
        SpringApplication.run(PaiprojektApplication.class, args);
    }

    @PostConstruct
    public void init() {
        //dao.save(new User("admin", "admin", "admin", passwordEncoder.encode("passwd")));
    }
}
