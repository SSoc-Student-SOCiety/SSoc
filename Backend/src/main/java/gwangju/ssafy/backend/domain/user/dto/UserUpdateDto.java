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
    private String userPassword;
    private String userNickName;
    private String userImage;
}
