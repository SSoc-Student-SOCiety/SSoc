package gwangju.ssafy.backend.domain.user.service;

import gwangju.ssafy.backend.domain.user.dto.*;
import gwangju.ssafy.backend.global.common.dto.*;
import gwangju.ssafy.backend.global.component.jwt.dto.TokenUserInfoDto;

public interface UserService {

    // 이메일 중복체크를 위해 이메일이 존재하는지 여부 조회
    void existsUserByUserEmail(String userEmail);

    // 회원가입 처리
    void signUpUser(UserSignUpRequestDto userSignUpRequestDto);

    // 로그인 처리
    TokenUserInfoDto loginCheckUser(UserLoginRequestDto loginUserDto);

    // 로그아웃 처리
    void logoutUser(String userEmail);

    // 유저정보에서 닉네임만 수정
    TokenUserInfoDto updateNickName(UserUpdateDto userUpdateDto);

    // 유저정보에서 프로필 이미지만 수정
    TokenUserInfoDto updateImage(UserUpdateDto userUpdateDto);

    // 유저정보에서 패스워드만 수정
    TokenUserInfoDto updatePassword(UserUpdateDto userUpdateDto);

    // 임시 비밀번호 발급 및 db에 임시 비밀번호 저장
    void tempPassword(UserDto userDto, MailCodeDto mailCodeDto);


    // id값을 받아와 해당 유저 정보 삭제
    void deleteUser(Long id);

}
