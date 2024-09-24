package practice.project.global.response;

import practice.project.global.exception.custom.ErrorCode;

public class ErrorResponse<T> extends Response<T> {
    public ErrorResponse(ErrorCode errorCode) {
        super(false, errorCode.getStatusCode(), errorCode.getMessage(), null);
    }
}
