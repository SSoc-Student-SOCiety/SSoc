package gwangju.ssafy.backend.domain.user.dto;

import lombok.*;

@AllArgsConstructor(access = AccessLevel.PROTECTED)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Builder
public class UserInfoDto {
    private String userEmail;
    private String userName;
    private String userNickname;
    private String userImage;
}
