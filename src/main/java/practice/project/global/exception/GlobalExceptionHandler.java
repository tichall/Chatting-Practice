package practice.project.global.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import practice.project.global.exception.custom.CustomException;
import practice.project.global.response.ErrorResponse;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(CustomException.class)
    public ResponseEntity<ErrorResponse> handleCustomException(CustomException e) {
        log.error("{} 예외 발생", e.getClass());

        return ResponseEntity.status(e.getErrorCode().getStatusCode())
                .body(new ErrorResponse(e.getErrorCode()));
    }
}
