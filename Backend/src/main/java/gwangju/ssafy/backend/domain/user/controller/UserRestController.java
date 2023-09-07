package gwangju.ssafy.backend.domain.user.controller;

import gwangju.ssafy.backend.domain.user.dto.UserLoginRequestDto;
import gwangju.ssafy.backend.domain.user.dto.UserLoginResponseDto;
import gwangju.ssafy.backend.domain.user.dto.UserSignUpRequestDto;
import gwangju.ssafy.backend.domain.user.dto.UserUpdateDto;
import gwangju.ssafy.backend.domain.user.service.UserService;
import gwangju.ssafy.backend.global.common.dto.Message;
import gwangju.ssafy.backend.global.common.dto.TokenDto;
import gwangju.ssafy.backend.global.common.dto.TokenRequestDto;
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
    @PostMapping("/mail/send")
    public String sendMail(@RequestParam(value = "userEmail") String email) throws Exception {
        String confirm = emailService.sendSimpleMessage(email);
        log.info("메일 전송 완료");
        return confirm;
    }

    // 이메일 중복 체크
//    @GetMapping("/email/check")
//    public ResponseEntity<?> checkEmailDuplication(@RequestParam(value = "userEmail") String userEmail) throws BadRequestException {
//        if(userService.existsUserByUserEmail(userEmail)) {
//            throw new BadRequestException("이미 사용중인 이메일 입니다.");
//        }
//        else {
//            return ResponseEntity.ok("사용 가능한 이메일 입니다.");
//        }
//    }

    @PostMapping("/signup")
    public ResponseEntity<Message> registerSuccess(@RequestBody @Valid UserSignUpRequestDto userSignUpRequestDto) {
        log.info("==================회원가입 진입=============");
        userService.signUpUser(userSignUpRequestDto);
        return ResponseEntity.ok().body(Message.success());
    }

    // 로그인
    @PostMapping("/login")
    public ResponseEntity<Message<UserLoginResponseDto>> login(@RequestBody UserLoginRequestDto userLoginRequestDto) {
        log.info("==================로그인 진입=============");
        UserLoginResponseDto userLoginResponseDto = userService.loginCheckUser(userLoginRequestDto);
        return ResponseEntity.ok(Message.success(userLoginResponseDto));
    }

    // JWT 토큰 재발급
    @PostMapping("/reissue")
    public ResponseEntity<TokenDto> reissue(@RequestBody TokenRequestDto tokenRequestDto) {
        log.info("==================JWT 토큰 재발급 진입=============");
        return ResponseEntity.ok(userService.reissue(tokenRequestDto));
    }

    @PostMapping("/update/nickName")
    public ResponseEntity<Message> updateNickName(@RequestBody UserUpdateDto userUpdateDto) {
        log.info("==================회원정보 닉네임 수정 Controller 진입=============");
        userService.updateNickName(userUpdateDto);
        return ResponseEntity.ok(Message.success());
    }

    @PostMapping("/update/password")
    public ResponseEntity<Message> updatePassword(@RequestBody UserUpdateDto userUpdateDto) {
        log.info("==================회원정보 비밀번호 수정 Controller 진입=============");
        if(userService.updatePassword(userUpdateDto)) {
            return ResponseEntity.ok(Message.success());
        }
        return ResponseEntity.ok(Message.fail(null, "비밀번호가 이전과 같습니다."));
    }

    @PostMapping("/update/image")
    public ResponseEntity<Message> updateImage(@RequestBody UserUpdateDto userUpdateDto) {
        log.info("==================회원정보 프로필 이미지 수정 Controller 진입=============");
        userService.updateImage(userUpdateDto);
        return ResponseEntity.ok(Message.success());
    }

    @PostMapping("/update/nickName/password")
    public ResponseEntity<Message> updateNickNameAndPassword(@RequestBody UserUpdateDto userUpdateDto) {
        log.info("==================회원정보 닉네임, 비밀번호 수정 Controller 진입=============");
        if(userService.updatePassword(userUpdateDto)) {
            userService.updateNickName(userUpdateDto);
            return ResponseEntity.ok(Message.success());
        }
        return ResponseEntity.ok(Message.fail(null, "비밀번호가 이전과 같습니다."));
    }

}
