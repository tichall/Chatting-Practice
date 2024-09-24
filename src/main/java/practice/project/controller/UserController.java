package practice.project.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import practice.project.dto.user.SignupRequestDto;
import practice.project.dto.user.SignupResponseDto;
import practice.project.global.response.Response;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/users")
public class UserController {

}
