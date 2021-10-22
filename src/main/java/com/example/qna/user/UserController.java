package com.example.qna.user;

import com.example.qna.config.auth.PrincipalDetail;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
