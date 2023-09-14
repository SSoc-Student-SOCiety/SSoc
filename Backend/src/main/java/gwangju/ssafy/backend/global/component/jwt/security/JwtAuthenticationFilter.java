package gwangju.ssafy.backend.global.component.jwt.security;

import gwangju.ssafy.backend.domain.user.dto.LoginActiveUserDto;
import gwangju.ssafy.backend.global.component.jwt.dto.TokenUserInfoDto;
import gwangju.ssafy.backend.global.component.jwt.service.JwtService;
import gwangju.ssafy.backend.global.exception.GlobalError;
import gwangju.ssafy.backend.global.exception.TokenException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

// import javax.servlet 할시 오류 발생 -> 원래 오라클이 관리하던 자바 EE를 2017년 손을 떼게 되면서 이후 이클립스가 인수하며 자카르타 EE로 명칭을 바꾸고 새롭게 변화했다고한다.
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;

@Slf4j
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {
    public static final String AUTHORIZATION_HEADER = "Authorization";
    public static final String BEARER_PREFIX = "Bearer ";

    public  static final String REFRESH_HEADER = "Refresh";

    private final JwtService jwtService;

    // 실제 필터링 로직은 doFilterInternal에 들어감
    // JWT 토큰의 인증 정보를 현재 쓰레드의 SecurityContext에 저장하는 역할 수행
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        // 1. Request Header 에서 토큰을 꺼냄
        String jwt = getJwtToken(request);
//        log.info(jwt);  // accessToekn값

        authenticate(request, jwt);

        filterChain.doFilter(request, response);
    }

    // Request Header 에서 토큰 정보를 꺼내오기
    public String getJwtToken(HttpServletRequest request) {
        String bearerToken = request.getHeader(AUTHORIZATION_HEADER);
//        log.info(bearerToken);
        // 쌍따옴표 붙여서 나오는 부분 제거
        if(bearerToken != null) {
            bearerToken = bearerToken.replaceAll("\"", "");
        }
        if (StringUtils.hasText(bearerToken) && bearerToken.startsWith(BEARER_PREFIX)) {
            return bearerToken.substring(7);
        }
        return null;
    }

    public void authenticate(HttpServletRequest request, String token) {
        TokenUserInfoDto tokenUserInfoDto = null;
        if(StringUtils.hasText(token)) {
            log.info(request.getHeader(AUTHORIZATION_HEADER).toString());
            tokenUserInfoDto = jwtService.parseAccessToken(token);
            try {
                LoginActiveUserDto loginActiveUserDto = LoginActiveUserDto.from(tokenUserInfoDto);
                saveLoginUserInSecurityContext(loginActiveUserDto);
            }
            catch(RuntimeException e){
                SecurityContextHolder.clearContext();
                log.info("INVALID_TOKEN");
                throw new TokenException(GlobalError.INVALID_TOKEN, e);
            }

//            if(tokenUserInfoDto != null) {
//                LoginActiveUserDto loginActiveUserDto = LoginActiveUserDto.from(tokenUserInfoDto);
//                log.info(loginActiveUserDto.getUserEmail());
//                saveLoginUserInSecurityContext(loginActiveUserDto);
//            }
//            else {
//
//            }

//            SecurityContextHolder.clearContext();
        }
    }

    private static void saveLoginUserInSecurityContext(LoginActiveUserDto loginActiveUserDto) {
        JwtAuthenticationToken authentication = new JwtAuthenticationToken(
                loginActiveUserDto, "", Arrays.asList(new SimpleGrantedAuthority(loginActiveUserDto.getRole()))
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);
    }

}
