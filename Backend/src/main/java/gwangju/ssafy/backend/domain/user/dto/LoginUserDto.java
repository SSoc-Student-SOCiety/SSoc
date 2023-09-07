package gwangju.ssafy.backend.domain.user.dto;

import gwangju.ssafy.backend.domain.user.entity.User;
import lombok.*;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor(access = AccessLevel.PROTECTED)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
@Getter
public class LoginUserDto {

    private String userEmail;
    private String userPassword;
    
}
