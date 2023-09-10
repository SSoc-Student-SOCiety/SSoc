package gwangju.ssafy.backend.global.component.jwt.dto;

import static javax.management.timer.Timer.ONE_MINUTE;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

import static gwangju.ssafy.backend.global.component.jwt.JwtUtils.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class TokenUserInfoDto {
    private Long id;
    private String userEmail;
    private String userName;
    private String userNickname;
    private String role;

    public Claims toClaims(int expiresMin) {
        Claims claims = Jwts.claims();

        Date now = new Date();

        claims.put(KEY_ID, this.id);
        claims.put(KEY_EMAIL, this.userEmail);
        claims.put(KEY_NAME, this.userName);
        claims.put(KEY_NICKNAME, this.userNickname);
        claims.put(KEY_ROLES, this.role);
        claims.setIssuedAt(now);
        claims.setExpiration(new Date(now.getTime() + expiresMin * ONE_MINUTE));

        // expire 시간은 TokenProvider에서 설정
        return claims;
    }

    public static TokenUserInfoDto from(Claims claims) {
        return TokenUserInfoDto.builder()
                .id(claims.get(KEY_ID, Long.class))
                .userEmail(claims.get(KEY_EMAIL, String.class))
                .userName(claims.get(KEY_NAME, String.class))
                .userNickname(claims.get(KEY_NICKNAME, String.class))
                .role(claims.get(KEY_ROLES, String.class))
                .build();
    }


}
