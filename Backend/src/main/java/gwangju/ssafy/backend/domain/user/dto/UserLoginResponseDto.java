package gwangju.ssafy.backend.domain.user.dto;

import gwangju.ssafy.backend.global.common.dto.TokenDto;
import lombok.*;

@AllArgsConstructor(access = AccessLevel.PROTECTED)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
@Getter
public class UserLoginResponseDto {
    private UserDto userInfo;
    private TokenDto token;
}
