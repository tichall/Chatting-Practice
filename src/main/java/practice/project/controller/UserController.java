package practice.project.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import practice.project.dto.user.SignupRequestDto;
import practice.project.dto.user.SignupResponseDto;
import practice.project.global.response.Response;
import practice.project.service.UserService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    @PostMapping("/signup")
    public ResponseEntity<Response<SignupResponseDto>> signup(@RequestBody SignupRequestDto requestDto) {
        SignupResponseDto responseDto = userService.signup(requestDto);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(Response.of(HttpStatus.CREATED.value(), "회원가입 완료", responseDto));
    }
}
