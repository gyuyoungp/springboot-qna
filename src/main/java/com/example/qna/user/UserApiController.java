package com.example.qna.user;

import com.example.qna.dto.ResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/api/users")
@RestController
public class UserApiController {

    private final UserService userService;

//    @GetMapping("")
//    public List<User> list() {
//        return userService.findAll();
//    }

    @PostMapping("")
    public ResponseDto<Integer> save(@RequestBody User user) {
        user.setRole(Role.USER);
        userService.save(user);
        return new ResponseDto<Integer>(HttpStatus.OK.value(), 1);
    }

/*    @PostMapping("/login")
    public ResponseDto<Integer> login(@RequestBody User user, HttpSession session) {
        System.out.println("UserApiController.login");
        User principal = userService.login(user);
        if (principal != null) {
            session.setAttribute("principal", principal);
        }
        return new ResponseDto<Integer>(HttpStatus.OK.value(), 1);
    }*/

/*    @PutMapping("")
    public ResponseDto<Integer> update(@RequestBody User user) {
        userService.update(user);
        return new ResponseDto<Integer>(HttpStatus.OK.value(), 1);
    }*/


//    @DeleteMapping("/{id}")
//    public void delete(@PathVariable Long id) {
//        userRepository.deleteById(id);
//    }
//

    //
//    @GetMapping("/page")
//    public List<User> pageList(@PageableDefault(size = 1, sort = "id", direction = Sort.Direction.DESC) Pageable pageable) {
//        Page<User> pagingUser = userRepository.findAll(pageable);
//        List<User> users = pagingUser.getContent();
//        return users;
//    }


}
