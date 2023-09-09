package gwangju.ssafy.backend.domain.user.service;

import gwangju.ssafy.backend.domain.user.dto.*;
import gwangju.ssafy.backend.global.common.dto.MailSendDto;
import gwangju.ssafy.backend.global.common.dto.TokenDto;
import gwangju.ssafy.backend.global.common.dto.TokenRequestDto;
import jakarta.servlet.http.HttpServletRequest;

public interface UserService {

    // 이메일 보내기
    void sendSimpleMessage(MailSendDto mailSendDto);

    // 이메일 중복체크를 위해 이메일이 존재하는지 여부 조회
    boolean existsUserByUserEmail(String userEmail);

    // 회원가입 처리
    void signUpUser(UserSignUpRequestDto userSignUpRequestDto);

    // 로그인 처리
    UserLoginResponseDto loginCheckUser(UserLoginRequestDto loginUserDto);

    void logoutUser(HttpServletRequest request);

    TokenDto reissue(TokenRequestDto tokenRequestDto);

    // 유저정보에서 닉네임만 수정
    void updateNickName(UserUpdateDto userUpdateDto);

    // 유저정보에서 프로필 이미지만 수정
    void updateImage(UserUpdateDto userUpdateDto);

    // 유저정보에서 패스워드만 수정
    boolean updatePassword(UserUpdateDto userUpdateDto);

}
