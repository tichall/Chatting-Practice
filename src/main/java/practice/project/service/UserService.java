package practice.project.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import practice.project.dto.user.SignupRequestDto;
import practice.project.dto.user.SignupResponseDto;

@Service
public interface UserService {
    public SignupResponseDto signup(SignupRequestDto requestDto);
}
