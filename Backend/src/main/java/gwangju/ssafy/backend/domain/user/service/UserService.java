package gwangju.ssafy.backend.domain.user.service;

import gwangju.ssafy.backend.domain.user.dto.LoginUserDto;
import gwangju.ssafy.backend.domain.user.dto.MailDto;
import gwangju.ssafy.backend.domain.user.dto.UserRequestDto;
import gwangju.ssafy.backend.global.common.dto.TokenDto;
import gwangju.ssafy.backend.global.common.dto.TokenRequestDto;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.data.redis.core.ValueOperations;

public interface UserService {

    // 이메일 보내기
    void sendSimpleMessage(MailDto mailDto);

    // 이메일 중복체크를 위해 이메일이 존재하는지 여부 조회
    boolean existsUserByUserEmail(String userEmail);

    // 회원가입 처리
    UserRequestDto signUpUser(UserRequestDto userRequestDto);

    // 로그인 처리
    TokenDto loginCheckUser(LoginUserDto loginUserDto);

    void logoutUser(HttpServletRequest request);

    TokenDto reissue(TokenRequestDto tokenRequestDto);
}
