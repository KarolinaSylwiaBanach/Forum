package com.example.paiprojekt.controller;

import com.example.paiprojekt.entity.Post;
import com.example.paiprojekt.entity.User;
import com.example.paiprojekt.services.postDao;
import com.example.paiprojekt.services.userDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.security.Principal;
import java.util.Optional;

@Controller
public class PostController {

    @Autowired
    private postDao postDao;

    @Autowired
    private userDao userDao;

    @GetMapping("/")
    public String forumPageRedirect(Model m) {
        return "redirect:/forum";
    }

    @GetMapping("/forum")
    public String forumPage(Model m) {
        m.addAttribute("postsList", postDao.findAllByOrderByCreatedAtAsc());
        m.addAttribute("post", new Post());
        return "forum";
    }

    @PostMapping("/post")
    public String postNew(@ModelAttribute(value = "post") Post post, Principal principal, RedirectAttributes redirectAttributes) {
        if(post.getContent().replaceAll("\\s+","").equals("")){
            redirectAttributes.addFlashAttribute("messageError", "Twój post jest pusty!");
            return "redirect:/forum";
        }
        User user = userDao.findByLogin(principal.getName());
        post.setUser(user);
        postDao.save(post);
        redirectAttributes.addFlashAttribute("message", "Twój post został dodany!");
        return "redirect:/forum";
    }
}
