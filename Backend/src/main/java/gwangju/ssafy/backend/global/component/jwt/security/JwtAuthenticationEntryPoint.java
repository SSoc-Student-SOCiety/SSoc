package gwangju.ssafy.backend.global.component.jwt.security;

import jakarta.servlet.ServletException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 자격 증명 없이 접근시, 401 응답을 보여줌
 */

@Slf4j
@Component
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        // 자격증명 없이 접근시, 401응답
        log.info("====================JwtAuthenticationEntryPoint=====================");
        log.info("자격 증명 없이 접근한 경우");
        response.sendError(HttpServletResponse.SC_UNAUTHORIZED);
    }
}
