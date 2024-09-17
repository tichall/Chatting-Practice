package practice.project.global.response;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class ResponseMessage<T> {
    private final int statusCode;
    private T data;

    public ResponseMessage<T> of(Integer statusCode, T data) {
        return new ResponseMessage<T>(statusCode, data);
    }

    public ResponseMessage<T> of(T data) {
        return new ResponseMessage<T>(200, data);
    }

}
