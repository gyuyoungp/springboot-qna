package com.example.qna.question;

import com.example.qna.config.auth.PrincipalDetail;
import com.example.qna.dto.ResponseDto;
import com.example.qna.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@RequestMapping("/api/questions")
@RequiredArgsConstructor
@RestController
public class QuestionApiController {

    private final QuestionService questionService;

    @PostMapping("")
    public ResponseEntity<Object> save(@RequestBody Question question, @AuthenticationPrincipal PrincipalDetail principal) {
        questionService.save(question, principal.getUser());
        return ResponseEntity.status(HttpStatus.OK).body(new ResponseDto(HttpStatus.OK.value(), "글 작성 완료"));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteById(@PathVariable Long id) {
        questionService.deleteById(id);
        return ResponseEntity.status(HttpStatus.OK).body(new ResponseDto(HttpStatus.OK.value(), "글 삭제 완료"));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> update(@PathVariable Long id, @RequestBody Question question) {
        questionService.update(id, question);
        return ResponseEntity.status(HttpStatus.OK).body(new ResponseDto(HttpStatus.OK.value(), "글 수정 완료"));
    }

}
