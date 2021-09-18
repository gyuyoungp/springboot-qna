package com.example.qna.controller;

import com.example.qna.domain.Answer;
import com.example.qna.domain.Question;
import com.example.qna.domain.User;
import com.example.qna.repository.AnswerRepository;
import com.example.qna.repository.QuestionRepository;
import com.example.qna.utils.HttpSessionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/questions/{questionId}/answers")
public class AnswerController {

    @Autowired
    private AnswerRepository answerRepository;

    @Autowired
    private QuestionRepository questionRepository;

    /**
     * 댓글 작성
     */
    @PostMapping("")
    public String create(@PathVariable Long questionId, String contents, HttpSession session) {

        User user = (User) session.getAttribute(HttpSessionUtils.USER_SESSION_KEY);
        if (user == null) {
            return "redirect:/users/login";
        }

        Question question = questionRepository.findById(questionId).get();
        if (user.equals(question.getId())) {
            return "redirect:/users/login";
        }
        answerRepository.save(new Answer(user, question, contents));
        return String.format("redirect:/questions/%d", questionId);
    }

    /**
     * 댓글 삭제
     */
    @DeleteMapping("/{id}")
    public String delete(@PathVariable Long questionId, @PathVariable Long id, HttpSession session) {
        User user = (User) session.getAttribute(HttpSessionUtils.USER_SESSION_KEY);
        if (user == null) {
            return "redirect:/users/login";
        }

        Answer answer = answerRepository.findById(id).get();
        if (!user.equals(answer.getWriter())) {
            return "redirect:/users/login";
        }
        answerRepository.delete(answer);

        return String.format("redirect:/questions/%d", questionId);
    }
}
