package gwangju.ssafy.backend.domain.user.dto;

import lombok.*;

@AllArgsConstructor(access = AccessLevel.PROTECTED)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
@Getter
public class UserDto {
    private Long id;
    private String userEmail;
    private String userNickName;
    private String userName;
}
