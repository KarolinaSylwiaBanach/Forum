package com.example.paiprojekt.controller;

import com.example.paiprojekt.services.userDao;
import com.example.paiprojekt.entity.User;

import java.security.Principal;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import javax.xml.validation.Validator;


@Controller
public class UserController {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private userDao dao;

    private Validator validator;

    @GetMapping("/login")
    public String loginPage() {
        return "login";
    }

    @GetMapping("/login-error")
    public String loginErrorPage(Model m) {
        m.addAttribute("loginError", "Błędy login lub hasło. Spróbuj ponownie.");
        return "login";
    }

    @GetMapping("/register")
    public String registerPage(Model m) {
        m.addAttribute("user", new User());
        return "register";
    }

    @PostMapping("/register")
    public String registerPagePOST(@ModelAttribute(value = "user") @Valid User user, BindingResult bindingResult, Model m) {

        if (dao.findByLogin(user.getLogin()) != null) {
            m.addAttribute("userExistMessage", "Użytkownik o podanej nazwie użytkownika już istnieje");
            return "register";
        }
        if(bindingResult.hasErrors()){
            return "register";
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        dao.save(user);
        return "redirect:/login";
    }

    @GetMapping("/profile")
    public String profilePage(Model m, Principal principal) {
        m.addAttribute("user", dao.findByLogin(principal.getName()));
        return "profile";
    }

    @GetMapping("/users")
    public String profilePage(Model m) {
        m.addAttribute("userlist", dao.findAll());
        return "users";
    }

    @GetMapping("/delete")
    public String delete(@RequestParam Integer id, Model m, Principal principal, RedirectAttributes redirectAttributes) {

        Optional<User> opt = dao.findById(id);

        if (opt.isPresent()) {
            User user = opt.get();
            if (principal.getName().equals(user.getLogin())) {
                SecurityContextHolder.getContext().setAuthentication(null);
                dao.deleteById(id);
                redirectAttributes.addFlashAttribute("deleteMessage", "Twoje konto zostało usunięte!");
                return "redirect:/login";
            } else {
                m.addAttribute("deleteMessage", "Błąd 1!");
                m.addAttribute("user", dao.findByLogin(principal.getName()));
                return "profile";
            }
        }
        m.addAttribute("deleteMessage", "Błąd 2!");
        m.addAttribute("user", dao.findByLogin(principal.getName()));
        return "profile";
    }

    @GetMapping("/edition")
    public String edition(Model m, Principal principal) {
        m.addAttribute("user", dao.findByLogin(principal.getName()));
        return "edition";
    }

    @PostMapping("/edition")
    public String edition(@ModelAttribute(value = "user") @Valid User newUser, BindingResult bindingResult, Model m, Principal principal) {


        if (bindingResult.hasErrors()) {
            //List<ObjectError> errors = bindingResult.getAllErrors();
            if (bindingResult.hasFieldErrors("name") || bindingResult.hasFieldErrors("surname")) {
                m.addAttribute("user", newUser);
                return "edition";
            }
        }

        User user = dao.findByLogin(principal.getName());
        user.setName(newUser.getName());
        user.setSurname(newUser.getSurname());
        dao.save(user);
        m.addAttribute("message", "Zminay zostały dokonane!");
        m.addAttribute("user", dao.findByLogin(principal.getName()));
        return "profile";
    }
}
