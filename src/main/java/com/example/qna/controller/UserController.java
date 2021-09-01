package com.example.qna.controller;

import com.example.qna.domain.User;
import com.example.qna.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/new")
    public String createForm() {
        return "users/userForm";
    }

    @PostMapping("/new")
    public String create(User user) {
        userRepository.save(user);
        System.out.println("user:" + user);
        return "redirect:/";
    }

    @GetMapping("")
    public String list(Model model) {
        model.addAttribute("users", userRepository.findAll());
        return "users/userList";
    }
}
