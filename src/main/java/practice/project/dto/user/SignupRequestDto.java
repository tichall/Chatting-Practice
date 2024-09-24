package practice.project.dto.user;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import practice.project.entity.User;
import practice.project.entity.enums.AuthProvider;
import practice.project.entity.enums.UserStatus;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class SignupRequestDto {
    private String email;
    private String password;
    private String name;
    private String nickname;

    public User toEntity() {
        return User.builder()
                .email(this.email)
                .password(this.password)
                .name(this.name)
                .nickname(this.nickname)
                .authProvider(AuthProvider.ORIGIN)
                .userStatus(UserStatus.ACTIVATE)
                .build();
    }
}
