package gwangju.ssafy.backend.global.exception;

import com.fasterxml.jackson.databind.ObjectMapper;
import gwangju.ssafy.backend.global.common.dto.Message;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.ErrorResponse;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Slf4j
public class ExceptionHandlerFilter extends OncePerRequestFilter {

    private ResponseEntity<Message> responseEntity = null;
    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain
    ) throws ServletException, IOException {
        try {
            filterChain.doFilter(request, response);
        } catch (TokenException e) {
            // 에러 세팅 (response, 해당 Exception이 발생했을 때 에러 코드)
            setErrorResponse(response, e.getErrorCode());
        }
    }

    private void setErrorResponse(HttpServletResponse response, ErrorCode errorCode) {
        ObjectMapper objectMapper = new ObjectMapper();
        log.info(errorCode.getErrorMessage());
        response.setStatus(errorCode.getHttpStatus().value());
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.setCharacterEncoding("utf-8"); // utf-8로 설정해줘야 한글 안깨짐
        try {
            response.getWriter().write(objectMapper.writeValueAsString(Message.fail(null, errorCode.getErrorMessage())));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
