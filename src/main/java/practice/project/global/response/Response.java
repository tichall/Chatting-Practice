package practice.project.global.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class Response<T> {
    private final boolean isSuccess;
    private final int statusCode;
    private final String message;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private T data;

    public static <T> Response<T> of(Integer statusCode) {
        return new Response<T>(true, statusCode, "Success.", null);
    }

    public static <T> Response<T> of(Integer statusCode, T data) {
        return new Response<T>(true, statusCode, "Success.", data);
    }

    public static <T> Response<T> of(String message, T data) {
        return new Response<T>(true, 200, message, data);
    }

    public static <T> Response<T> of(Integer statusCode, String message, T data) {
        return new Response<T>(true, statusCode, message, data);
    }
}
