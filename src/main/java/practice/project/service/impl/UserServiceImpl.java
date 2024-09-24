package practice.project.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import practice.project.dto.user.SignupRequestDto;
import practice.project.dto.user.SignupResponseDto;
import practice.project.entity.User;
import practice.project.global.exception.custom.CustomException;
import practice.project.global.exception.custom.UserErrorCode;
import practice.project.repository.UserRepository;
import practice.project.service.UserService;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    @Transactional
    public SignupResponseDto signup(SignupRequestDto requestDto) {
        String email = requestDto.getEmail();
        if(userRepository.existsByEmail(email)) {
            throw new CustomException(UserErrorCode.DUPLICATE_EMAIL);
        }

        User newUser = requestDto.toEntity();
        userRepository.save(newUser);
        return SignupResponseDto.of(newUser);
    }
}
