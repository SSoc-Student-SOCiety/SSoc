package gwangju.ssafy.backend.domain.user.dto;

import gwangju.ssafy.backend.domain.user.entity.User;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.*;

// 유저 서비스 요청 RequestDTO 클래스
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
@Setter
public class UserDto {
    private Long id;

    @NotBlank(message = "이메일은 필수 입력값입니다.")
    @Email(message = "이메일 형식이 올바르지 않습니다")
    private String userEmail;

    @NotBlank(message = "비밀번호는 필수 입력값입니다.")
    @Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[$@$!%*#?&])[A-Za-z\\d$@$!%*#?&]{8,16}$", message = "비밀번호는 8~16자리수여야 합니다. 영문 대소문자, 숫자, 특수문자를 1개 이상 포함해야 합니다.")
    private String userPassword;

    @NotBlank(message = "이름은 필수 입력값입니다.")
    private String userName;

    @NotBlank(message = "닉네임은 필수 입력값입니다.")
    @Pattern(regexp = "^[가-힣a-zA-Z0-9]{2,10}$" , message = "닉네임은 특수문자를 포함하지 않은 2~10자리여야 합니다.")
    private String userNickName;

    // DTO -> Entity
    public User toEntity() {
        User user = User.builder()
                .id(id)
                .userEmail(userEmail)
                .userName(userName)
                .userPassword(userPassword)
                .userNickNmae(userNickName)
                .build();
        return user;
    }

}
