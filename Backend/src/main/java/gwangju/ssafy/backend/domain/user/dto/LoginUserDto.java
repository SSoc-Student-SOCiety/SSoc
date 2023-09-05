package gwangju.ssafy.backend.domain.user.dto;

import lombok.*;

@AllArgsConstructor(access = AccessLevel.PROTECTED)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
@Getter
public class LoginUserDto {

    private String userEmail;
    private String userPassword;
    
    // 나중에 토큰값 집어넣기 작업 해야됨
}
