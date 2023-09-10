package gwangju.ssafy.backend.domain.user.dto;

import lombok.*;

@AllArgsConstructor(access = AccessLevel.PROTECTED)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Builder
public class UserInfoDto {
    private String userEmail;
    private String userPassword;
    private String userName;
    private String userNickName;
    private String userImage;
}
