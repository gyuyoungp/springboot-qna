package com.example.qna.user;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

@RequestMapping("/users")
@Controller
public class UserController {

    @GetMapping("/createForm")
    public String createForm() {
        return "users/createForm";
    }

    @GetMapping("/login")
    public String loginForm() {
        return "users/login";
    }

/*    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.removeAttribute("principal");
        return "redirect:/users/login";
    }*/

/*    @GetMapping("/update")
    public String updateForm(Model model, HttpSession session) {
        User principal = (User) session.getAttribute("principal");
        if (principal == null) return "redirect:/users/login";
        model.addAttribute("principal", principal);
        return "users/updateForm";
    }*/
}
