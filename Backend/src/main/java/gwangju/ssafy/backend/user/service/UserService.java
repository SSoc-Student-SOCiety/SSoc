package gwangju.ssafy.backend.user.service;

import gwangju.ssafy.backend.user.dto.MailDto;

public interface UserService {

    // 이메일 보내기
    public void sendSimpleMessage(MailDto mailDto);

    // 이메일 중복체크를 위해 이메일이 존재하는지 여부 조회
    public boolean existsUserByUserEmail(String userEmail);

    // 학교이름 - 이메일 해시맵 이용 (테스트)
    public void schoolEmailTest(String schoolName, String schoolEmail);

    // 학교 이메일인지 여부 판단해주기 (테스트)
    public boolean checkSchoolEmail(String email);
}
