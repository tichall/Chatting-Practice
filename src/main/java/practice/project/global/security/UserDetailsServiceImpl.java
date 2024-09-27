package practice.project.global.security;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import practice.project.entity.User;
import practice.project.entity.enums.UserStatus;
import practice.project.global.exception.custom.CustomException;
import practice.project.global.exception.custom.UserErrorCode;
import practice.project.repository.UserRepository;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {
    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(username)
                .orElseThrow(() -> new CustomException(UserErrorCode.USER_NOT_FOUND));

        if(user.getUserStatus().equals(UserStatus.DEACTIVATE)) {
            throw new CustomException(UserErrorCode.DEACTIVATE_USER);
        } else if(user.getUserStatus().equals(UserStatus.WITHDRAWAL)) {
            throw new CustomException(UserErrorCode.WITHDRAWAL_USER);
        }

        return new UserDetailsImpl(user);
    }
}
