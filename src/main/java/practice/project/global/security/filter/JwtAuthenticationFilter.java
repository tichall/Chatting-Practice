package practice.project.global.security.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.FilterChain;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import practice.project.dto.user.LoginRequestDto;
import practice.project.entity.User;
import practice.project.entity.enums.UserStatus;
import practice.project.global.exception.custom.SecurityErrorCode;
import practice.project.global.jwt.JwtProvider;
import practice.project.global.security.UserDetailsImpl;
import practice.project.global.util.ResponseUtil;

import java.io.IOException;

@Slf4j
public class JwtAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private final JwtProvider jwtProvider;

    public JwtAuthenticationFilter(JwtProvider jwtProvider) {
        this.jwtProvider = jwtProvider;
        setFilterProcessesUrl("/api/users/login");
    }

    /**
     * 로그인 시도
     */
    @Override
    public Authentication attemptAuthentication(HttpServletRequest request,
                                                HttpServletResponse response) throws AuthenticationException {
        try {
            LoginRequestDto requestDto = new ObjectMapper()
                    .readValue(request.getInputStream(), LoginRequestDto.class);

            // 여기에서 내부적으로 UserDetailsService의 load
            return getAuthenticationManager().authenticate(
                    new UsernamePasswordAuthenticationToken(
                            requestDto.getEmail(),
                            requestDto.getPassword(),
                            null
                    )
            );
        } catch (IOException e) {
            log.error(e.getMessage());
            throw new RuntimeException(e.getMessage());
        }
    }

    /**
     * 로그인 성공 및 JWT 생성
     */
    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response,
                                            FilterChain chain, Authentication authResult) throws IOException {
        UserDetailsImpl userDetails = (UserDetailsImpl) authResult.getPrincipal();
        User loginUser = userDetails.getUser();

        if (loginUser.getUserStatus() == UserStatus.ACTIVATE) {

            String userEmail = loginUser.getEmail();
            String accessToken = jwtProvider.createAccessToken(userEmail);
            String refreshToken = jwtProvider.createRefreshToken(userEmail);

            // 응답 헤더에 토큰 추가
            response.addHeader(JwtProvider.AUTHORIZATION_HEADER, accessToken);
            response.addHeader(JwtProvider.REFRESH_HEADER, refreshToken);

            // JSON 응답 작성
            ResponseUtil.writeJsonResponse(response, HttpStatus.OK, "로그인에 성공했습니다.", authResult.getName());

            log.debug("User = {}, message = {}", userEmail, "로그인에 성공했습니다.");
        } else {
            ResponseUtil.writeJsonErrorResponse(response, SecurityErrorCode.WITHDRAWAL_USER);
        }
    }

    /**
     * 로그인 실패
     */
    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request,
                                              HttpServletResponse response, AuthenticationException failed) throws IOException {
        log.debug("로그인 실패 : {}", failed.getMessage());
        ResponseUtil.writeJsonErrorResponse(response, SecurityErrorCode.LOGIN_FAILED);
    }

}
