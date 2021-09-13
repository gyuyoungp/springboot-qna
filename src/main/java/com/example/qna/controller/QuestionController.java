package com.example.qna.controller;

import com.example.qna.domain.Question;
import com.example.qna.domain.User;
import com.example.qna.repository.QuestionRepository;
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
@RequestMapping("/questions")
public class QuestionController {

    @Autowired
    private QuestionRepository questionRepository;

    @GetMapping("/form")
    public String questionForm(HttpSession session) {
        User user = (User) session.getAttribute(HttpSessionUtils.USER_SESSION_KEY);
        if (user == null) {
            return "redirect:/users/login";
        }
        return "questions/form";
    }

    @PostMapping("/form")
    public String question(String title, String contents, HttpSession session) {
        User user = (User) session.getAttribute(HttpSessionUtils.USER_SESSION_KEY);
        if (user == null) {
            return "redirect:/users/login";
        }

        Question question = new Question(user, title, contents);
        questionRepository.save(question);
        System.out.println(question);

        return "redirect:/";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable Long id, Model model) {
        model.addAttribute("question", questionRepository.findById(id).get());
        return "questions/show";
    }

    @GetMapping("/{id}/form")
    public String updateForm(@PathVariable Long id, Model model, HttpSession session) {
        User user = (User) session.getAttribute(HttpSessionUtils.USER_SESSION_KEY);
        if (user == null) {
            return "redirect:/users/login";
        }
        Question question = questionRepository.findById(id).get();
        if (user.equals(question.getId())) {
            return "redirect:/users/login";
        }

        model.addAttribute("question", question);
        return "questions/updateForm";
    }

    @PostMapping("/{id}")
    public String update(@PathVariable Long id, String title, String contents, HttpSession session) {

        User user = (User) session.getAttribute(HttpSessionUtils.USER_SESSION_KEY);
        if (user == null) {
            return "redirect:/users/login";
        }
        Question question = questionRepository.findById(id).get();
        if (user.equals(question.getId())) {
            return "redirect:/users/login";
        }

        question.update(title, contents);
        questionRepository.save(question);
        return String.format("redirect:/questions/%d", id);
    }

    @PostMapping("/{id}/delete")
    public String delete(@PathVariable Long id, HttpSession session) {

        User user = (User) session.getAttribute(HttpSessionUtils.USER_SESSION_KEY);
        if (user == null) {
            return "redirect:/users/login";
        }
        Question question = questionRepository.findById(id).get();
        if (user.equals(question.getId())) {
            return "redirect:/users/login";
        }

        questionRepository.delete(question);
        return "redirect:/";
    }

}
