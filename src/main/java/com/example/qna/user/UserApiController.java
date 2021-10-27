package com.example.qna.user;

import com.example.qna.dto.ResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RequestMapping("/api/users")
@RestController
public class UserApiController {

    private final UserService userService;

    @PostMapping("")
    public ResponseEntity<Object> save(@RequestBody User user) {
        userService.save(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(new ResponseDto(HttpStatus.CREATED.value(), "회원가입 성공"));
    }

    @PutMapping("")
    public ResponseEntity<Object> update(@RequestBody User user) {
        userService.update(user);
        Authentication newAuth = new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword());
        SecurityContextHolder.getContext().setAuthentication(newAuth);
        return ResponseEntity.status(HttpStatus.OK).body(new ResponseDto(HttpStatus.OK.value(), "회원정보 수정 성공"));
    }
}
