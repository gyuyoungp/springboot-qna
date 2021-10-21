package com.example.qna.answer;

import com.example.qna.dto.ResponseDto;
import com.example.qna.question.Question;
import com.example.qna.question.QuestionRepository;
import com.example.qna.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@RequiredArgsConstructor
@RequestMapping("/api/questions/{questionId}/answers")
@RestController
public class AnswerApiController {

    private final AnswerService answerService;

    @PostMapping("")
    public ResponseDto<Integer> save(@PathVariable Long questionId, @RequestBody Answer answer, HttpSession session) {
        User principal = (User) session.getAttribute("principal");
        if (principal == null) return new ResponseDto<>(HttpStatus.OK.value(), 0);
        answerService.save(questionId, answer, principal);
        return new ResponseDto<>(HttpStatus.OK.value(), 1);
    }

    @DeleteMapping("/{answerId}")
    public ResponseDto<Integer> deleteById(@PathVariable Long answerId) {
        answerService.delete(answerId);
        return new ResponseDto<>(HttpStatus.OK.value(), 1);
    }

}
