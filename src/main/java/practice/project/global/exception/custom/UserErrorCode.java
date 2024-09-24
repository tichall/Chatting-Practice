package practice.project.global.exception.custom;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum UserErrorCode implements ErrorCode {
    DUPLICATE_EMAIL(400, "이미 가입된 이메일입니다.");

    private final int statusCode;
    private final String message;
}
