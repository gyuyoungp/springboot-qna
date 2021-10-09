package com.example.qna.user;

import com.example.qna.utils.HttpSessionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    /**
     * 회원가입 폼 이동
     */
    @GetMapping("/new")
    public String createForm() {
        return "users/createForm";
    }

    /**
     * 회원가입
     */
    @PostMapping("")
    public String create(User user) {
        userRepository.save(user);
        return "redirect:/";
    }

    /**
     * 유저 목록 조회
     */
    @GetMapping("")
    public String list(Model model) {
        model.addAttribute("users", userRepository.findAll());
        return "users/list";
    }

    /**
     * 로그인 폼 이동
     */
    @GetMapping("/login")
    public String loginForm() {
        return "users/login";
    }

    /**
     * 로그인
     */
    @PostMapping("/login")
    public String login(String userId, String password, HttpSession session) {
        User user = userRepository.findByUserId(userId);
        if (user == null || !password.equals(user.getPassword()))
            return "redirect:/users/login";
        session.setAttribute(HttpSessionUtils.USER_SESSION_KEY, user);
        return "redirect:/";
    }

    /**
     * 로그아웃
     */
    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.removeAttribute(HttpSessionUtils.USER_SESSION_KEY);
        return "redirect:/";
    }

    /**
     * 유저 정보수정 폼 이동
     */
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

    /**
     * 유저 정보수정
     */
    @PutMapping("/{userId}")
    public String update(@PathVariable String userId, User updateUser, HttpSession session) {
        System.out.println("찍힌다");
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
