package gwangju.ssafy.backend.global.component.jwt;


import gwangju.ssafy.backend.global.exception.ErrorCode;
import gwangju.ssafy.backend.global.exception.TokenException;
import io.jsonwebtoken.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import java.security.Key;

@Slf4j
@Component
public class JwtParser {

    public Claims parseToken(String token, Key sercretKey) {
        Claims claims = null;
        try {
            claims = Jwts.parserBuilder()
                    .setSigningKey(sercretKey)
                    .build()
                    .parseClaimsJws(token).getBody();
        }
        catch (io.jsonwebtoken.security.SecurityException | MalformedJwtException | UnsupportedJwtException | IllegalArgumentException e) {
//            log.info("잘못된 JWT 서명입니다. (유효하지 않은 인증 토큰입니다)");
            throw new TokenException(ErrorCode.INVALID_TOKEN, e);
        } catch (ExpiredJwtException e) {

//            log.info("만료된 JWT 토큰입니다.");
//                String refreshToken = request.getHeader(JwtAuthenticationFilter.REFRESH_HEADER);
//                if(StringUtils.hasText(refreshToken) && refreshToken.startsWith((JwtAuthenticationFilter.BEARER_PREFIX))) {
//                    refreshToken = refreshToken.substring(7);
//                    TokenDto tokenDto = null;
//                    try {
//                        // AcessToken과 RefreshToken 재발급
//                        tokenDto = jwtParserService.refreshToken(refreshToken);
//                    }
//                    catch (Exception ex) {
//                        throw new TokenException(ErrorCode.EXPIRED_TOKEN, ex);
//                    }
//                }
//                log.info("==============RefreshToekn을 이용해 AccessToken과 RefreshToken 재발급================");
//                log.info(refreshToken);
//                claims = Jwts.parserBuilder()
//                        .setSigningKey(sercretKey)
//                        .build()
//                        .parseClaimsJws(refreshToken).getBody();

            throw new TokenException(ErrorCode.ACCESS_EXPIRED_TOKEN, e);
        }

        return claims;
    }
}
