package gwangju.ssafy.backend.domain.user.controller;

import gwangju.ssafy.backend.domain.user.dto.LoginUserDto;
import gwangju.ssafy.backend.domain.user.dto.UserRequestDto;
import gwangju.ssafy.backend.domain.user.service.UserService;
import gwangju.ssafy.backend.global.common.dto.TokenDto;
import gwangju.ssafy.backend.global.common.dto.TokenRequestDto;
import gwangju.ssafy.backend.global.infra.email.EmailService;
import gwangju.ssafy.backend.domain.user.exception.BadRequestException;
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
    @GetMapping("/email/check")
    public ResponseEntity<?> checkEmailDuplication(@RequestParam(value = "userEmail") String userEmail) throws BadRequestException {
        if(userService.existsUserByUserEmail(userEmail)) {
            throw new BadRequestException("이미 사용중인 아이디 입니다.");
        }
        else {
            return ResponseEntity.ok("사용 가능한 아이디 입니다.");
        }
    }

    @PostMapping("/signup")
    public ResponseEntity<UserRequestDto> registerSuccess(@RequestBody UserRequestDto userRequestDto) {
        log.info("==================회원가입 진입=============");
        return ResponseEntity.ok(userService.signUpUser(userRequestDto));
    }

    // 로그인
    @PostMapping("/login")
    public ResponseEntity<TokenDto> login(@RequestBody LoginUserDto loginUserDto) {
        log.info("==================로그인 진입=============");
        return ResponseEntity.ok(userService.loginCheckUser(loginUserDto));
    }

    // JWT 토큰 재발급
    @PostMapping("/reissue")
    public ResponseEntity<TokenDto> reissue(@RequestBody TokenRequestDto tokenRequestDto) {
        return ResponseEntity.ok(userService.reissue(tokenRequestDto));
    }

}
