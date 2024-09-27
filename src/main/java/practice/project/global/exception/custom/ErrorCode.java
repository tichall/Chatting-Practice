package practice.project.global.exception.custom;

import lombok.Getter;

public interface ErrorCode {
    int getStatusCode();
    String getMessage();
}
