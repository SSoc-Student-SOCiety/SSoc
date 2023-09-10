package gwangju.ssafy.backend.domain.user.dto;

import gwangju.ssafy.backend.global.component.jwt.dto.TokenDto;
import gwangju.ssafy.backend.global.component.jwt.dto.TokenUserInfoDto;
import lombok.*;

@AllArgsConstructor(access = AccessLevel.PROTECTED)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
@Getter
public class UserLoginResponseDto {
    private TokenUserInfoDto userInfo;
    private TokenDto token;
}
