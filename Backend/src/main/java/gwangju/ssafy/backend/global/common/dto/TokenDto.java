package gwangju.ssafy.backend.global.common.dto;

import lombok.*;

// 로그인, 재발급 응답 토큰 dto
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TokenDto {
    private String grantType;
    private String accessToken;
    private String refreshToken;
    private Long accessTokenExpiresIn;
}
