package practice.project.service;

import org.springframework.stereotype.Service;
import practice.project.dto.user.SignupRequestDto;
import practice.project.dto.user.SignupResponseDto;

@Service
public interface UserService {
    SignupResponseDto signup(SignupRequestDto requestDto);
}
