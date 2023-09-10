package gwangju.ssafy.backend.global.component.jwt;

import io.jsonwebtoken.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.security.Key;

@Slf4j
@RequiredArgsConstructor
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
        catch (io.jsonwebtoken.security.SecurityException | MalformedJwtException e) {
            log.info("잘못된 JWT 서명입니다.");
        } catch (ExpiredJwtException e) {
            log.info("만료된 JWT 토큰입니다.");
        } catch (UnsupportedJwtException e) {
            log.info("지원되지 않는 JWT 토큰입니다.");
        } catch (IllegalArgumentException e) {
            log.info("JWT 토큰이 잘못되었습니다.");
        }

        return claims;
    }
}
