package gwangju.ssafy.backend.global.component.jwt.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import gwangju.ssafy.backend.global.common.dto.Message;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;

import static gwangju.ssafy.backend.global.exception.GlobalError.NOT_AUTHORITY_USER_API;

/**
 * 권한 없이 접근시, 403 응답을 보여줌
 */
@Slf4j
@Component
public class JwtAccessDeniedHandler implements AccessDeniedHandler {
    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {
        // 권한 없이 접근시, 403 응답
        log.info("====================JwtAccessDeniedHandler=====================");
        log.info("권한 없이 접근한 경우");

        setResponse(response);

        ObjectMapper objectMapper = new ObjectMapper();
        response.getWriter().write(objectMapper.writeValueAsString(Message.fail(null, NOT_AUTHORITY_USER_API.getErrorMessage())));
    }

    private void setResponse(HttpServletResponse response) {
        response.setStatus(NOT_AUTHORITY_USER_API.getHttpStatus().value());
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.setCharacterEncoding("utf-8");
    }
}
