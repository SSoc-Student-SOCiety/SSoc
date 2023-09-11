package gwangju.ssafy.backend.domain.user.dto;

import lombok.*;

@AllArgsConstructor(access = AccessLevel.PROTECTED)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class UserLoginRequestDto {
    private String userEmail;
    private String userPassword;
    
}
