package practice.project.global.exception.custom;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum UserErrorCode implements ErrorCode {
    DEACTIVATE_USER(400, "잠긴 계정입니다."),
    WITHDRAWAL_USER(400, "탈퇴한 계정입니다."),
    USER_NOT_FOUND(404, "찾을 수 없는 사용자입니다."),
    DUPLICATE_EMAIL(400, "이미 가입된 이메일입니다.");

    private final int statusCode;
    private final String message;
}
