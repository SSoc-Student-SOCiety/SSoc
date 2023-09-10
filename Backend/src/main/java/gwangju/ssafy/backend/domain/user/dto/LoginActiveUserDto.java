package gwangju.ssafy.backend.domain.user.dto;

import gwangju.ssafy.backend.global.component.jwt.dto.TokenUserInfoDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.AccessLevel;

@AllArgsConstructor(access = AccessLevel.PROTECTED)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Builder
public class LoginActiveUserDto {
    private Long id;
    private String userEmail;
    private String userName;
    private String userNickname;
    private String role;

    public static LoginActiveUserDto from(TokenUserInfoDto info) {
        return LoginActiveUserDto.builder()
                .id(info.getId())
                .userEmail(info.getUserEmail())
                .userName(info.getUserName())
                .userNickname(info.getUserNickname())
                .role(info.getRole())
                .build();

    }

}
