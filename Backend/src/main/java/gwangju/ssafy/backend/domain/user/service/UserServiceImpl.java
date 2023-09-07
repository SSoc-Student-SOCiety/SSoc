package gwangju.ssafy.backend.domain.user.service;

import gwangju.ssafy.backend.domain.user.dto.*;
import gwangju.ssafy.backend.domain.user.entity.User;
import gwangju.ssafy.backend.domain.user.repository.UserRepository;
import gwangju.ssafy.backend.global.common.dto.TokenDto;
import gwangju.ssafy.backend.global.common.dto.TokenRequestDto;
import gwangju.ssafy.backend.global.component.jwt.TokenProvider;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@Slf4j
@Service
@Transactional  // DB 관련 모든 작업은 트랜잭션 안에서 수행되어야 한다
@RequiredArgsConstructor
class UserServiceImpl implements UserService {

    private final AuthenticationManagerBuilder authenticationManagerBuilder;

    private final UserRepository userRepository;

    private final JavaMailSender emailSender;

    private final PasswordEncoder passwordEncoder;

    private final TokenProvider tokenProvider;

    private final StringRedisTemplate redisTemplate;



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
    public void signUpUser(UserSignUpRequestDto userRequestDto) {
        if(userRepository.existsUserByUserEmail(userRequestDto.getUserEmail())) {
            throw new RuntimeException("이미 가입되어 있는 이메일입니다.");
        }

        userRequestDto.setUserPassword(passwordEncoder.encode(userRequestDto.getUserPassword())); // 패스워드 암호화 작업
        User user = userRepository.save(userRequestDto.toEntity());
        log.info(user.getId().toString());
    }

    // 로그인 처리 부분
    @Override
    public UserLoginResponseDto loginCheckUser(UserLoginRequestDto userLoginRequestDto) {
        User user = userRepository.findByUserEmail(userLoginRequestDto.getUserEmail()).orElseThrow(() ->
                new IllegalArgumentException("해당 이메일을 가진 회원이 존재하지 않습니다. 이메일을 다시한번 확인해주세요."));
        String realPassword = user.getUserPassword();

        if(!passwordEncoder.matches(userLoginRequestDto.getUserPassword(), realPassword)) {
            throw new IllegalArgumentException("비밀번호가 틀렸습니다. 다시한번 확인해주세요.");
        }

        // Dto의 email, 비밀번호을 받고 UssrnamePasswordAuthenticationToken 객체 생성
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(userLoginRequestDto.getUserEmail(), realPassword);
        log.info(String.valueOf(authenticationToken));
        log.info(authenticationToken.getCredentials().toString());
        Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);

        TokenDto tokenDto = tokenProvider.generateTokenDto(authentication);

        log.info(user.getUserEmail());
        log.info(user.getUsername());
        log.info(user.getUserNickName());
        log.info(user.getUserPassword());

        UserDto userDto = UserDto.builder()
                .userEmail(user.getUserEmail())
                .userNickName(user.getUserNickName())
                .userName(user.getUsername())
                .build();

        return UserLoginResponseDto.builder()
                .userInfo(userDto)
                .token(tokenDto)
                .build();
    }

    @Override
    public void logoutUser(HttpServletRequest request) {
        // accessToken redisTemplate 블랙리스트 추가
        String jwt = request.getHeader("Authorization").substring(7);
        ValueOperations<String, String> logoutValueOperations = redisTemplate.opsForValue();
        logoutValueOperations.set(jwt, jwt);    // redis set 명령어
    }

    // 재발급
    @Transactional
    public TokenDto reissue(TokenRequestDto tokenRequestDto) {
        // refresh Token 검증
        if(!tokenProvider.validateToken(tokenRequestDto.getRefreshToken())) {
            throw new RuntimeException("Refresh Token이 유효하지 않습니다.");
        }

        // access Token에서 Authentication 객체 가져오기
        Authentication authentication = tokenProvider.getAuthentication(tokenRequestDto.getAccessToken());

        // 새로운 토큰 재발급해서 반환
        return tokenProvider.generateTokenDto(authentication);
    }

    // 회원정보에서 닉네임만 수정
    @Override
    public void updateNickName(UserUpdateDto userUpdateDto) {
        User user = userRepository.findByUserEmail(userUpdateDto.getUserEmail()).get();
        user.updateUserNickName(userUpdateDto.getUserNickName());
    }

    // 회원정보에서 프로필 이미지만 수정
    @Override
    public void updateImage(UserUpdateDto userUpdateDto) {
        User user = userRepository.findByUserEmail(userUpdateDto.getUserEmail()).get();
        user.updateUserImage(userUpdateDto.getUserImage());
    }

    // 회원정보에서 비밀번호만 수정
    @Override
    public boolean updatePassword(UserUpdateDto userUpdateDto) {
        User user = userRepository.findByUserEmail(userUpdateDto.getUserEmail()).get();
        String realPassword = user.getUserPassword();
        if(passwordEncoder.matches(userUpdateDto.getUserPassword(), realPassword)) {
            return false;
        }
        user.updatePassword(passwordEncoder.encode(userUpdateDto.getUserPassword()));
        return true;
    }

}
