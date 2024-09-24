package practice.project.global.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import practice.project.global.exception.custom.ErrorCode;
import practice.project.global.response.ErrorResponse;
import practice.project.global.response.Response;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class ResponseUtil {

    private static final ObjectMapper objectMapper = new ObjectMapper();

    /**
     * HttpResponse에 JSON 형태로 응답하기
     */
    public static void writeJsonResponse(HttpServletResponse response, HttpStatus status,
                                         String message, String data) throws IOException {
        Response<String> responseMessage = Response.of(200, message);

        String jsonResponse = objectMapper.writeValueAsString(responseMessage);
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json; charset=UTF-8");
        response.getWriter().write(jsonResponse);
    }

    /**
     * HttpResponse에 JSON 형태로 응답하기
     */
    public static void writeJsonErrorResponse(HttpServletResponse response, ErrorCode errorCode)
            throws IOException {
        ErrorResponse<Void> responseMessage = new ErrorResponse<>(errorCode);

        String jsonResponse = objectMapper.writeValueAsString(responseMessage);

        response.setCharacterEncoding(StandardCharsets.UTF_8.name());
        response.setContentType(
                MediaType.APPLICATION_JSON_VALUE + "; charset=" + StandardCharsets.UTF_8.name());
        response.setStatus(errorCode.getStatusCode());
        response.getWriter().write(jsonResponse);
    }
}