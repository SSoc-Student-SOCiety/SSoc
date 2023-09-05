package gwangju.ssafy.backend.domain.user.service;

import gwangju.ssafy.backend.domain.user.dto.LoginUserDto;
import gwangju.ssafy.backend.domain.user.dto.MailDto;
import gwangju.ssafy.backend.domain.user.dto.UserDto;
import gwangju.ssafy.backend.domain.user.entity.User;
import gwangju.ssafy.backend.domain.user.entity.enums.UserRole;
import gwangju.ssafy.backend.domain.user.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;

@Service
@Transactional  // DB 관련 모든 작업은 트랜잭션 안에서 수행되어야 한다
@RequiredArgsConstructor
class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    private final JavaMailSender emailSender;

    private final PasswordEncoder passwordEncoder;


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
    @Override
    public boolean existsUserByUserEmail(String userEmail) {
        return userRepository.existsUserByUserEmail(userEmail);
    }


    // 회원가입 처리
    @Override
    public boolean signUpUser(UserDto userDto) {
        if(userRepository.existsUserByUserEmail(userDto.getUserEmail())) {
            return false;
        }

        userDto.setUserPassword(passwordEncoder.encode(userDto.getUserPassword())); // 패스워드 암호화 작업
        userDto.setUserAuthority(UserRole.USER.toString()); // 회원가입한 유저의 권한은 USER (일반)
        userRepository.save(userDto.toEntity());
        return true;

    }

    @Override
    public boolean loginCheckUser(LoginUserDto loginUserDto) {
        User user = userRepository.findByUserEmail(loginUserDto.getUserEmail()).orElseThrow(() ->
                new IllegalArgumentException("해당 이메일을 가진 회원이 존재하지 않습니다. 이메일을 다시한번 확인해주세요."));
        String realPassword = user.getUserPassword();

        return passwordEncoder.matches(loginUserDto.getUserPassword(), realPassword);
    }



}
