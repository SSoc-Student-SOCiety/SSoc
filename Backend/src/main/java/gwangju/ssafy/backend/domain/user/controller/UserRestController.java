package gwangju.ssafy.backend.domain.user.controller;

import gwangju.ssafy.backend.domain.user.dto.UserDto;
import gwangju.ssafy.backend.domain.user.service.UserService;
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

    @GetMapping("/email/check")
    public ResponseEntity<?> checkEmailDuplication(@RequestParam(value = "userEmail") String userEmail) throws BadRequestException {
        if(userService.existsUserByUserEmail(userEmail)) {
            throw new BadRequestException("이미 사용중인 아이디 입니다.");
        }
        else {
            return ResponseEntity.ok("사용 가능한 아이디 입니다.");
        }
    }

    @PostMapping("/register")
    public boolean registerSuccess(@RequestBody UserDto userDto) {
        log.info("==================회원가입 진입=============");
        if(userService.existsUserByUserEmail(userDto.getUserEmail())) {
            return false;
        }
        userService.signUpUser(userDto);
        return true;
    }


}
