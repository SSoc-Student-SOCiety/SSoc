package gwangju.ssafy.backend.domain.user.service;

import gwangju.ssafy.backend.domain.user.dto.*;
import gwangju.ssafy.backend.domain.user.entity.User;
import gwangju.ssafy.backend.domain.user.repository.UserRepository;
import gwangju.ssafy.backend.global.common.dto.*;
import gwangju.ssafy.backend.global.component.jwt.dto.TokenUserInfoDto;
import gwangju.ssafy.backend.global.exception.ErrorCode;
import gwangju.ssafy.backend.global.exception.UserException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@Slf4j
@Service
@Transactional  // DB 관련 모든 작업은 트랜잭션 안에서 수행되어야 한다
@RequiredArgsConstructor
class UserServiceImpl implements UserService {


    private final UserRepository userRepository;

    private final JavaMailSender emailSender;

    private final PasswordEncoder passwordEncoder;



    // 이메일 보내기
    @Override
    public void sendSimpleMessage(MailSendDto mailSendDto) {
        SimpleMailMessage message = new SimpleMailMessage();
//        message.setFrom("tlsehdrms124@yonsei.ac.kr"); // 구글 정책 때문에 발신자(보내는사람) 세팅 안됨

        message.setTo(mailSendDto.getAddress());    // 수신자(받는사람) 이메일 주소 세팅
        message.setSubject(mailSendDto.getTitle()); // 이메일 제목 세팅
        message.setText(mailSendDto.getContent());  // 이메일 내용 세팅
        emailSender.send(message);  // 이메일 보내기
    }

    // 이에일 중복 체크를 위해 이메일이 존재하는지 여부 조회
    @Override
    public void existsUserByUserEmail(String userEmail) {
        if(userRepository.existsUserByUserEmail(userEmail)) {
            throw new UserException(ErrorCode.EXIST_USER_EMAIL);
        }
    }


    // 회원가입 처리
    @Override
    public void signUpUser(UserSignUpRequestDto userRequestDto) {
        if(userRepository.existsUserByUserEmail(userRequestDto.getUserEmail())) {
            throw new UserException(ErrorCode.EXIST_USER_EMAIL);
        }
        userRequestDto.setUserPassword(passwordEncoder.encode(userRequestDto.getUserPassword())); // 패스워드 암호화 작업
        userRepository.save(userRequestDto.toEntity());
    }

    // 로그인 처리 부분
    @Override
    public TokenUserInfoDto loginCheckUser(UserLoginRequestDto userLoginRequestDto) {

        // 해당 이메일을 가진 유저 찾을 수 없을 경우 UserException 발생
        User user = userRepository.findByUserEmail(userLoginRequestDto.getUserEmail()).orElseThrow(() ->
                new UserException(ErrorCode.NOT_FOUND_USER));
        String realPassword = user.getUserPassword();


        if(!passwordEncoder.matches(userLoginRequestDto.getUserPassword(), realPassword)) {
            throw new UserException(ErrorCode.NOT_MATCH_PASSWORD);
        }
        return TokenUserInfoDto.builder()
                .id(user.getId())
                .userEmail(user.getUserEmail())
                .userNickname(user.getUserNickname())
                .userName(user.getUserName())
                .role(user.getRole().toString())
                .build();
    }

    // 회원정보에서 닉네임만 수정
    @Override
    public TokenUserInfoDto updateNickName(UserUpdateDto userUpdateDto) {
        User user = userRepository.findByUserEmail(userUpdateDto.getUserEmail()).orElseThrow(() ->
                new UserException(ErrorCode.NOT_FOUND_USER));
        // 해당 엔티티 접근해서 유저 닉네임 업데이트
        user.updateUserNickname(userUpdateDto.getUserNickName());
        return TokenUserInfoDto.update(user);
    }

    // 회원정보에서 프로필 이미지만 수정
    @Override
    public TokenUserInfoDto updateImage(UserUpdateDto userUpdateDto) {
        User user = userRepository.findByUserEmail(userUpdateDto.getUserEmail()).orElseThrow(() ->
                new UserException(ErrorCode.NOT_FOUND_USER));
        user.updateUserImage(userUpdateDto.getUserImage());
        return TokenUserInfoDto.update(user);
    }

    // 회원정보에서 비밀번호만 수정
    @Override
    public TokenUserInfoDto updatePassword(UserUpdateDto userUpdateDto) {
        User user = userRepository.findByUserEmail(userUpdateDto.getUserEmail()).get();
        String realPassword = user.getUserPassword();   // 해당 유저 db에서 검색해서 현재 저장된 패스워드 받아옴
        // 입력받은 현재 패스워드와 DB내에 저장된 패스워드가 같지 않은 경우 (현재 비밀번호를 잘못 입력하였습니다)
        if(!passwordEncoder.matches(userUpdateDto.getUserNowPassword(), realPassword)) {
            throw new UserException(ErrorCode.NOT_MATCH_PASSWORD);
        }

        // 입력받은 유저의 변경할 비밀번호가 DB내에 저장된 패스워드와 같은 경우 (현재 비밀번호가 변경하려는 비밀번호와 같습니다)
        if(passwordEncoder.matches(userUpdateDto.getUserChangePassword(), realPassword)) {
            throw new UserException(ErrorCode.CURRENT_CHANGE_MATCH_PASSWORD);
        }

        user.updatePassword(passwordEncoder.encode(userUpdateDto.getUserChangePassword()));
        return TokenUserInfoDto.update(user);
    }

    // 임시 비밀번호 발급 및 db에 임시 비밀번호 저장
    @Override
    public void tempPassword(UserDto userDto, MailCodeDto mailCodeDto) {
        User user = userRepository.findByUserEmail(userDto.getUserEmail()).orElseThrow(() ->
                new UserException(ErrorCode.NOT_FOUND_USER));
        user.updatePassword(passwordEncoder.encode(mailCodeDto.getEmailCode()));
    }

    // 유저 이메일을 통해 회원정보 불러오기
//    @Override
//    public TokenUserInfoDto userInformationFind(String userEmail) {
//        User user = userRepository.findByUserEmail(userEmail).orElseThrow(() ->
//                new UserException(ErrorCode.NOT_FOUND_USER));
//        return TokenUserInfoDto.builder()
//                .id(user.getId())
//                .userEmail(user.getUserEmail())
//                .userName(user.getUserName())
//                .userNickname(user.getUserNickname())
//                .userImageUrl(user.getUserImageUrl())
//                .build();
//    }

    // 유저 아이디를 통한 회원정보 삭제
    @Override
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

}
