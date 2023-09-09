package gwangju.ssafy.backend.domain.user.controller;

import gwangju.ssafy.backend.domain.user.dto.*;
import gwangju.ssafy.backend.domain.user.service.UserService;
import gwangju.ssafy.backend.global.common.dto.*;
import gwangju.ssafy.backend.global.infra.email.EmailService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@RequestMapping("user")
public class UserRestController {
    @Autowired
    private UserService userService;

    @Autowired
    private EmailService emailService;

    // 이메일을 통한 인증코드 보내기
    @PostMapping("/email/send")
    public ResponseEntity<Message<MailCodeDto>> sendMail(@RequestBody UserDto userDto) {
        MailCodeDto mailCodeDto = null;
        try {
            mailCodeDto = emailService.sendSimpleMessage(userDto.getUserEmail(), true);
        } catch (Exception e) {
            return ResponseEntity.ok().body(Message.fail(null, e.getMessage()));
        }
        log.info("메일 전송 완료");
        return ResponseEntity.ok().body(Message.success(mailCodeDto));
    }

    // 이메일 중복 체크
    @PostMapping("/email/check")
    public ResponseEntity<Message> checkEmailDuplication(@RequestBody UserDto userDto) {
        log.info("==================이메일 중복체크 진입=============");
        log.info(userDto.getUserEmail());
        if(userService.existsUserByUserEmail(userDto.getUserEmail())) {
            return ResponseEntity.ok(Message.fail(null, "해당 이메일이 존재합니다."));
        }
        return ResponseEntity.ok().body(Message.success());
    }

    @PostMapping("/signup")
    public ResponseEntity<Message> signUp(@RequestBody @Valid UserSignUpRequestDto userSignUpRequestDto) {
        log.info("==================회원가입 진입=============");
        try {
            userService.signUpUser(userSignUpRequestDto);
        }
        catch (RuntimeException e) {
            return ResponseEntity.ok().body(Message.fail(null, e.getMessage()));
        }
        return ResponseEntity.ok().body(Message.success());
    }

    // 로그인
    @PostMapping("/login")
    public ResponseEntity<Message<UserLoginResponseDto>> login(@RequestBody UserLoginRequestDto userLoginRequestDto) {
        log.info("==================로그인 진입=============");
        UserLoginResponseDto userLoginResponseDto = null;
        try {
            userLoginResponseDto = userService.loginCheckUser(userLoginRequestDto);
        } catch (RuntimeException e) {
            return ResponseEntity.ok().body(Message.fail(null, e.getMessage()));
        }
        return ResponseEntity.ok().body(Message.success(userLoginResponseDto));
    }

    // JWT 토큰 재발급
//    @PostMapping("/reissue")
//    public ResponseEntity<TokenDto> reissue(@RequestBody TokenRequestDto tokenRequestDto) {
//        log.info("==================JWT 토큰 재발급 진입=============");
//        return ResponseEntity.ok().body(userService.reissue(tokenRequestDto));
//    }

    @PostMapping("/update/nickname")
    public ResponseEntity<Message> updateNickName(@RequestBody UserUpdateDto userUpdateDto) {
        log.info("==================회원정보 닉네임 수정 Controller 진입=============");
        userService.updateNickName(userUpdateDto);
        return ResponseEntity.ok().body(Message.success());
    }

    @PostMapping("/update/password")
    public ResponseEntity<Message> updatePassword(@RequestBody UserUpdateDto userUpdateDto) {
        log.info("==================회원정보 비밀번호 수정 Controller 진입=============");
        int code = userService.updatePassword(userUpdateDto);
        if(code == 0) {
            return ResponseEntity.ok().body(Message.success());
        }
        else if(code == 1) {
            return ResponseEntity.ok().body(Message.fail("1", "현재 비밀번호를 잘못 입력하였습니다."));
        }
        else {
            return ResponseEntity.ok().body(Message.fail("2", "현재 비밀번호가 변경하려는 비밀번호와 같습니다."));
        }
    }

    @PostMapping("/update/image")
    public ResponseEntity<Message> updateImage(@RequestBody UserUpdateDto userUpdateDto) {
        log.info("==================회원정보 프로필 이미지 수정 Controller 진입=============");
        userService.updateImage(userUpdateDto);
        return ResponseEntity.ok().body(Message.success());
    }

    @PostMapping("/update/nickname/password")
    public ResponseEntity<Message> updateNickNameAndPassword(@RequestBody UserUpdateDto userUpdateDto) {
        log.info("==================회원정보 닉네임, 비밀번호 수정 Controller 진입=============");
        int code = userService.updatePassword(userUpdateDto);
        if(code == 0) {
            userService.updateNickName(userUpdateDto);
            return ResponseEntity.ok().body(Message.success());
        }
        else if(code == 1) {
            return ResponseEntity.ok().body(Message.fail("1", "현재 비밀번호를 잘못 입력하였습니다."));
        }
        else {
            return ResponseEntity.ok().body(Message.fail("2", "현재 비밀번호가 변경하려는 비밀번호와 같습니다."));
        }


    }

    // 토큰값들(accessToken, refreshToken 받아오기)
    @PostMapping("/token/check")
    public ResponseEntity<Message> tokenCheck(@RequestBody TokenDto tokenDto) {
        log.info("==================토큰값 받아오기 Controller 진입=============");
        return ResponseEntity.ok().body(Message.success());
    }

    // 임시 비밀번호 재발급
    @PostMapping("/email/password")
    public ResponseEntity<Message> sendTempPassword(@RequestBody UserDto userDto) {
        MailCodeDto mailCodeDto = null;
        try {
            mailCodeDto = emailService.sendSimpleMessage(userDto.getUserEmail(), false);
        } catch (Exception e) {
            return ResponseEntity.ok().body(Message.fail(null, e.getMessage()));
        }
        log.info("메일 전송 완료");
        userService.tempPassword(userDto, mailCodeDto);
        log.info("임시 비밀번호 발급 완료");
        return ResponseEntity.ok().body(Message.success());
    }


}
