package com.example.qna.user;

import com.example.qna.dto.ResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RequestMapping("/api/users")
@RestController
public class UserApiController {

    private final UserService userService;

    @PostMapping("")
    public ResponseDto<Integer> save(@RequestBody User user) {
        userService.save(user);
        return new ResponseDto<>(HttpStatus.OK.value(), 1);
    }

    @PutMapping("")
    public ResponseDto<Integer> update(@RequestBody User user) {
        userService.update(user);
        return new ResponseDto<Integer>(HttpStatus.OK.value(), 1);
    }
}
