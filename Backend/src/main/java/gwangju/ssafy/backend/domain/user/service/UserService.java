package gwangju.ssafy.backend.domain.user.service;

import gwangju.ssafy.backend.domain.user.dto.MailDto;
import gwangju.ssafy.backend.domain.user.dto.UserDto;

public interface UserService {

    // 이메일 보내기
    public void sendSimpleMessage(MailDto mailDto);

    // 이메일 중복체크를 위해 이메일이 존재하는지 여부 조회
    public boolean existsUserByUserEmail(String userEmail);

    // 회원가입 처리
    public boolean signUpUser(UserDto userDto);

    // 로그인 처리
    public boolean loginCheckUser(String userEmail, String checkPassword);

}
