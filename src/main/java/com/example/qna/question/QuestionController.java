package com.example.qna.question;

import com.example.qna.user.User;
import com.example.qna.utils.HttpSessionUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@RequiredArgsConstructor
@RequestMapping("/questions")
@Controller
public class QuestionController {

    private final QuestionService questionService;

    @GetMapping("/createForm")
    public String createForm(HttpSession session) {
        User user = (User) session.getAttribute("principal");
        if (user == null) {
            return "redirect:/users/login";
        }
        return "questions/createForm";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable Long id, Model model) {
        model.addAttribute("question", questionService.findById(id));
        return "questions/show";
    }

    @GetMapping("/{id}/update")
    public String updateForm(@PathVariable Long id, Model model, HttpSession session) {
        User principal = (User) session.getAttribute("principal");
        if (principal == null) return "redirect:/users/login";
        model.addAttribute("question", questionService.findById(id));
        return "questions/updateForm";
    }


//    @DeleteMapping("/{id}")
//    public String delete(@PathVariable Long id, HttpSession session) {
//
//        User user = (User) session.getAttribute(HttpSessionUtils.USER_SESSION_KEY);
//        if (user == null) {
//            return "redirect:/users/login";
//        }
//        Question question = questionRepository.findById(id).get();
//        if (user.equals(question.getId())) {
//            return "redirect:/users/login";
//        }
//
//        questionRepository.delete(question);
//        return "redirect:/";
//    }

}
