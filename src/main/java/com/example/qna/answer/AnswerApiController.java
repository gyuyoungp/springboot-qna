package com.example.qna.answer;

import com.example.qna.config.auth.PrincipalDetail;
import com.example.qna.dto.ResponseDto;
import com.example.qna.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@RequiredArgsConstructor
@RequestMapping("/api/questions/{questionId}/answers")
@RestController
public class AnswerApiController {

    private final AnswerService answerService;

    @PostMapping("")
    public ResponseEntity<Object> save(@PathVariable Long questionId, @RequestBody Answer answer, @AuthenticationPrincipal PrincipalDetail principalDetail) {
        answerService.save(questionId, answer, principalDetail.getUser());
        return ResponseEntity.status(HttpStatus.OK).body(new ResponseDto(HttpStatus.OK.value(), "댓글 작성 완료"));

    }

    @DeleteMapping("/{answerId}")
    public ResponseEntity<Object> deleteById(@PathVariable Long answerId) {
        answerService.delete(answerId);
        return ResponseEntity.status(HttpStatus.OK).body(new ResponseDto(HttpStatus.OK.value(), "댓글 삭제 완료"));
    }

}
