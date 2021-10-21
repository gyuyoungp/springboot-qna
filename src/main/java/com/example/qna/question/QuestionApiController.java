package com.example.qna.question;

import com.example.qna.dto.ResponseDto;
import com.example.qna.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@RequestMapping("/api/questions")
@RequiredArgsConstructor
@RestController
public class QuestionApiController {

    private final QuestionService questionService;

    @PostMapping("")
    public ResponseDto<Integer> save(@RequestBody Question question, HttpSession session) {
        User principal = (User) session.getAttribute("principal");
        if (principal != null) questionService.save(question, principal);
        return new ResponseDto<>(HttpStatus.OK.value(), 1);
    }

    @DeleteMapping("/{id}")
    public ResponseDto<Integer> deleteById(@PathVariable Long id) {
        questionService.deleteById(id);
        return new ResponseDto<>(HttpStatus.OK.value(), 1);
    }

    @PutMapping("/{id}")
    public ResponseDto<Integer> update(@PathVariable Long id, @RequestBody Question question) {
        questionService.update(id, question);
        return new ResponseDto<>(HttpStatus.OK.value(), 1);
    }

}
