package gwangju.ssafy.backend.global.component.jwt;


import gwangju.ssafy.backend.global.component.jwt.security.JwtAuthenticationFilter;
import gwangju.ssafy.backend.global.exception.GlobalError;
import gwangju.ssafy.backend.global.exception.TokenException;
import io.jsonwebtoken.*;
import io.jsonwebtoken.io.DecodingException;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.security.Key;

import static gwangju.ssafy.backend.global.component.jwt.JwtUtils.KEY_EMAIL;

@Slf4j
@Component
public class JwtParser {

    @Autowired
    HttpServletRequest request;

    public Claims parseToken(String token, Key sercretKey) {
        Claims claims = null;

        try {
            claims = Jwts.parserBuilder()
                    .setSigningKey(sercretKey)
                    .build()
                    .parseClaimsJws(token).getBody();
//            log.info(claims.toString());
            log.info(claims.get(KEY_EMAIL, String.class));

        } catch (io.jsonwebtoken.security.SecurityException | MalformedJwtException | UnsupportedJwtException |
                 IllegalArgumentException e) {
            log.info("잘못된 JWT 서명입니다. (유효하지 않은 인증 토큰입니다)");
            throw new TokenException(GlobalError.INVALID_TOKEN, e);
        } catch (ExpiredJwtException e) {

//            log.info("만료된 JWT 토큰입니다.");
            String refreshToken = request.getHeader(JwtAuthenticationFilter.REFRESH_HEADER);
//            log.info(refreshToken);
            log.info("리프레쉬토큰 확인중");
            // 쌍따옴표 붙여서 나오는 부분 제거
            if(refreshToken != null) {
                refreshToken = refreshToken.replaceAll("\"", "");
            }

            log.info(refreshToken);
            if (StringUtils.hasText(refreshToken) && refreshToken.startsWith((JwtAuthenticationFilter.BEARER_PREFIX))) {
                refreshToken = refreshToken.substring(7);
                try {
                    claims = Jwts.parserBuilder()
                            .setSigningKey(sercretKey)
                            .build()
                            .parseClaimsJws(refreshToken).getBody();
                    log.info(claims.get(KEY_EMAIL, String.class));
                }
                // RefreshToken도 만료된 경우 둘다 만료됐다는 Exception 발생
                catch(Exception exception){
                    log.info("EXPIRED_TOKEN");
                    throw new TokenException(GlobalError.EXPIRED_TOKEN, exception);
                }
                log.info("ACCESS_EXPIRED_TOKEN");
                // AccessToken만 만료된 경우
                throw new TokenException(GlobalError.ACCESS_EXPIRED_TOKEN, e);
            }
        }
        log.info("만료안됨");
        // AccessToken 만료 안된 경우
        return claims;
    }

}
