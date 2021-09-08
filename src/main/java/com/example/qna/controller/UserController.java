package com.example.qna.controller;

import com.example.qna.domain.User;
import com.example.qna.repository.UserRepository;
import com.example.qna.utils.HttpSessionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/new")
    public String createForm() {
        return "/users/form";
    }

    @PostMapping("/new")
    public String create(User user) {
        userRepository.save(user);
        return "redirect:/";
    }

    @GetMapping("")
    public String list(Model model) {
        model.addAttribute("users", userRepository.findAll());
        return "users/list";
    }

    @GetMapping("/login")
    public String loginForm() {
        return "users/login";
    }

    @PostMapping("/login")
    public String login(String userId, String password, HttpSession session) {
        User user = userRepository.findByUserId(userId);
        if (user == null || !password.equals(user.getPassword()))
            return "redirect:/users/login";
        session.setAttribute(HttpSessionUtils.USER_SESSION_KEY, user);
        return "redirect:/";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.removeAttribute(HttpSessionUtils.USER_SESSION_KEY);
        return "redirect:/";
    }

    @GetMapping("/{userId}/update")
    public String updateForm(@PathVariable String userId, Model model, HttpSession session) {

        User user = (User) session.getAttribute(HttpSessionUtils.USER_SESSION_KEY);
        if (user == null) {
            return "redirect:/users/loginForm";
        }

        if (!userId.equals(user.getUserId())) {
            throw new IllegalStateException("You can't update");
        }

        model.addAttribute("user", user);
        return "users/updateForm";
    }

    @PostMapping("/{userId}/update")
    public String update(@PathVariable String userId, User updateUser, HttpSession session) {
        User user = (User) session.getAttribute(HttpSessionUtils.USER_SESSION_KEY);
        if (user == null) {
            return "redirect:/users/login";
        }

        if (!userId.equals(user.getUserId())) {
            throw new IllegalStateException("You can't update");
        }

        if (!updateUser.getPassword().equals(user.getPassword())) {
            return "redirect:/";
        }

        user.update(updateUser);
        userRepository.save(user);
        return "redirect:/";
    }
}
