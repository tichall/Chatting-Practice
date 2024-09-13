package practice.project.global.response;

import lombok.AllArgsConstructor;
import lombok.Builder;

@AllArgsConstructor
public class BasicResponse<T> {
    private Integer statusCode;
    private T data;

    public BasicResponse<T> of(Integer statusCode, T data) {
        return new BasicResponse<T>(statusCode, data);
    }

    public BasicResponse<T> of(T data) {
        return new BasicResponse<T>(200, data);
    }
    
}
