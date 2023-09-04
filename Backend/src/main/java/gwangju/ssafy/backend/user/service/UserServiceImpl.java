package gwangju.ssafy.backend.user.service;

import gwangju.ssafy.backend.user.dto.MailDto;
import gwangju.ssafy.backend.user.dto.UserDto;
import gwangju.ssafy.backend.user.entity.User;
import gwangju.ssafy.backend.user.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.HashMap;

@Service
@Transactional  // DB 관련 모든 작업은 트랜잭션 안에서 수행되어야 한다
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    private final JavaMailSender emailSender;

    // 이메일 보내기
    @Override
    public void sendSimpleMessage(MailDto mailDto) {
        SimpleMailMessage message = new SimpleMailMessage();
//        message.setFrom("tlsehdrms124@yonsei.ac.kr"); // 구글 정책 때문에 발신자(보내는사람) 세팅 안됨

        message.setTo(mailDto.getAddress());    // 수신자(받는사람) 이메일 주소 세팅
        message.setSubject(mailDto.getTitle()); // 이메일 제목 세팅
        message.setText(mailDto.getContent());  // 이메일 내용 세팅
        emailSender.send(message);  // 이메일 보내기
    }

    // 이에일 중복 체크를 위해 이메일이 존재하는지 여부 조회
    @Transactional
    @Override
    public boolean existsUserByUserEmail(String userEmail) {
        return userRepository.existsUserByUserEmail(userEmail);
    }


    // 학교이름 - 이메일 해시맵 이용해서 테스트
    @Override
    public void schoolEmailTest(String schoolName, String schoolEmail) {
        HashMap<String, String> schoolEmailHashMap = new HashMap<>();

        schoolEmailHashMap.put(schoolName, schoolEmail);
    }

//    @Override
//    public boolean checkSchoolEmail(String email) {
//        return userRepository.checkSchoolEmail(email);
//    }

    // 회원가입 처리
    @Override
    public boolean signUpUser(UserDto userDto) {
        if(userRepository.existsUserByUserEmail(userDto.getUserEmail())) {
            return false;
        }

        userRepository.save(userDto.toEntity());
        return true;
  
//    @Override
//    public boolean checkSchoolEmail(String email) {
//        return userRepository.checkSchoolEmail(email);
    }


}
