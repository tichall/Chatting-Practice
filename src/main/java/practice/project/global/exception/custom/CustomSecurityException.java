package practice.project.global.exception.custom;

public class CustomSecurityException extends CustomException{
    public CustomSecurityException(ErrorCode errorCode) {
        super(errorCode);
    }
}
