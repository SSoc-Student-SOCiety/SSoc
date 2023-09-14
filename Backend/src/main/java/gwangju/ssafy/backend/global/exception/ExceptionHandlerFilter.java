package gwangju.ssafy.backend.global.exception;

import com.fasterxml.jackson.databind.ObjectMapper;
import gwangju.ssafy.backend.domain.user.dto.UserLoginResponseDto;
import gwangju.ssafy.backend.global.common.dto.Message;
import gwangju.ssafy.backend.global.component.jwt.dto.TokenDto;
import gwangju.ssafy.backend.global.component.jwt.dto.TokenUserInfoDto;
import gwangju.ssafy.backend.global.component.jwt.security.JwtAuthenticationFilter;
import gwangju.ssafy.backend.global.component.jwt.service.JwtService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Slf4j
public class ExceptionHandlerFilter extends OncePerRequestFilter {

    @Autowired
    private JwtService jwtService;

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain
    ) throws ServletException, IOException {
        try {
            filterChain.doFilter(request, response);    // 다음 필터 실행
        } catch (TokenException e) {
            // 에러 세팅 (response, 해당 Exception이 발생했을 때 에러 코드)
            setErrorResponse(response, e.getErrorCode(), request);
        }
    }

    private void setErrorResponse(HttpServletResponse response, GlobalError errorCode, HttpServletRequest request) {
        ObjectMapper objectMapper = new ObjectMapper();
        log.info(errorCode.getErrorMessage());
        response.setStatus(errorCode.getHttpStatus().value());
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.setCharacterEncoding("utf-8"); // utf-8로 설정해줘야 한글 안깨짐
        try {
            log.info(errorCode.toString());
            log.info(request.getRequestURI());
//            log.info(request.getServletPath());

            String refreshToken = request.getHeader(JwtAuthenticationFilter.REFRESH_HEADER);
            // 쌍따옴표 붙여서 나오는 부분 제거
            if(refreshToken != null) {
                refreshToken = refreshToken.replaceAll("\"", "");
            }
            if (StringUtils.hasText(refreshToken) && refreshToken.startsWith((JwtAuthenticationFilter.BEARER_PREFIX))) {
                refreshToken = refreshToken.substring(7);
            }
            // 로그아웃 요청인 경우
            if(request.getRequestURI().equals("/user/logout")) {
                try {
                    // refresh 토큰을 이용하여 토큰 복호화 작업
                    TokenUserInfoDto tokenUserInfoDto = jwtService.decryptionRefreshToken(refreshToken);
                    // redis에 저장된 해당 refresh 토큰 삭제
                    jwtService.deleteRefreshToken(tokenUserInfoDto.getUserEmail());
                    response.getWriter().write(objectMapper.writeValueAsString(Message.success()));
                }
                // redis에 삭제 오류난 경우 (즉, redis에 해당 유저의 refresh 토큰값 없음) -> 그냥 로그아웃 성공처리
                catch(Exception e) {
                    response.getWriter().write(objectMapper.writeValueAsString(Message.success()));
                }
            }
            // 로그아웃 요청 아닌 경우
            else {
                log.info(errorCode.toString());
                // AccessToken 만료되고 RefreshToken은 살아있는 경우
                if(errorCode.toString().equals("ACCESS_EXPIRED_TOKEN")) {
                    TokenDto tokenDto = null;
                    try {
                        // refresh 토큰을 이용하여 Access, Refresh 토큰 재발급
                        tokenDto = jwtService.refreshToken(refreshToken);
                    }
                    // 토큰 재발급했는데 오류 발생한 경우 (즉, 받아온 Refresh 토큰이 redis에 저장되어 있지 않거나(즉, 만료되어 삭제)
                    // 또는 redis에 저장된 Refresh 토큰과 파싱해서 받아온 Refresh 토큰에서 유효성 검사에서 맞지 않은 경우
                    catch(TokenException e) {
                        response.getWriter().write(objectMapper.writeValueAsString(Message.fail(null, errorCode.getErrorMessage())));
                    }

                    TokenUserInfoDto tokenUserInfoDto = null;
                    try {
                        // 생성한 Refresh 토큰을 이용하여 토큰 복호화 작업
                        tokenUserInfoDto = jwtService.decryptionRefreshToken(tokenDto.getRefreshToken());
                    }
                    // 토큰 복호화 시 오류 발생하면
                    catch(Exception e) {
                        response.getWriter().write(objectMapper.writeValueAsString(Message.fail(null, errorCode.getErrorMessage())));
                    }

//                    try {
//                        // refresh 토큰을 이용하여 토큰 복호화 작업
//                        tokenUserInfoDto = jwtService.decryptionRefreshToken(refreshToken);
//                    }
//                    // 토큰 복호화 시 오류 발생하면
//                    catch(Exception e) {
//                        response.getWriter().write(objectMapper.writeValueAsString(Message.fail(null, errorCode.getErrorMessage())));
//                    }

                    UserLoginResponseDto userLoginResponseDto = UserLoginResponseDto.builder()
                            .userInfo(tokenUserInfoDto)
                            .token(tokenDto)
                            .build();
                    response.getWriter().write(objectMapper.writeValueAsString(Message.success(userLoginResponseDto, "1", errorCode.getErrorMessage())));
                }
                else {
                    response.getWriter().write(objectMapper.writeValueAsString(Message.fail("2", errorCode.getErrorMessage())));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
