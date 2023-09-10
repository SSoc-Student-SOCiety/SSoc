package gwangju.ssafy.backend.domain.user.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Data
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class UserUpdateDto {
    private String userEmail;
    private String userNowPassword; // 현재 비밀번호
    private String userChangePassword;  // 변경할 비밀번호
    private String userNickName;
    private String userImage;
}
