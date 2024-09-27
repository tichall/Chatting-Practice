package practice.project.dto.user;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import practice.project.entity.User;

@Getter
@AllArgsConstructor
public class SignupResponseDto {
    private Long userId;
    private String email;

    public static SignupResponseDto of(User user) {
        return new SignupResponseDto(user.getId(), user.getEmail());
    }

}
