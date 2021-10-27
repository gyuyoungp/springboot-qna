package com.example.qna.user;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/users")
@Controller
public class UserController {

    @GetMapping("/createForm")
    public String createForm() {
        return "users/createForm";
    }

    @GetMapping("/loginForm")
    public String loginForm() {
        return "users/loginForm";
    }

    @GetMapping("/updateForm")
    public String updateForm() {
        return "users/updateForm";
    }
}
