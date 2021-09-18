package com.example.qna.controller;

import com.example.qna.repository.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {

    @Autowired
    private QuestionRepository questionRepository;

    @GetMapping("/")
    public String root(Model model) {
        model.addAttribute("questions", questionRepository.findAll());
        return "index";
    }
}