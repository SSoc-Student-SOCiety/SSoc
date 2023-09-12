package gwangju.ssafy.backend.global.component.jwt.dto;

import static javax.management.timer.Timer.ONE_MINUTE;

import gwangju.ssafy.backend.domain.user.dto.LoginActiveUserDto;
import gwangju.ssafy.backend.domain.user.entity.User;
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
    private String userImageUrl;
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

    // 유저 업데이트 시 사용할 static 메서드
    public static TokenUserInfoDto update(User user) {
        return TokenUserInfoDto.builder()
                .id(user.getId())
                .userEmail(user.getUserEmail())
                .userNickname(user.getUserNickname())
                .userName(user.getUserName())
                .userImageUrl(user.getUserImageUrl())
                .role(user.getRole().toString())
                .build();
    }

    // 로그인 활성화 유저 변환
    public static TokenUserInfoDto convert(LoginActiveUserDto info) {
        return TokenUserInfoDto.builder()
                .id(info.getId())
                .userEmail(info.getUserEmail())
                .userName(info.getUserName())
                .userNickname(info.getUserNickname())
                .userImageUrl(info.getUserImageUrl())
                .role(info.getRole())
                .build();
    }


}
