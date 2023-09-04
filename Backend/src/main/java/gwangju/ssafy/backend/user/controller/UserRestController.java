package gwangju.ssafy.backend.user.controller;

import gwangju.ssafy.backend.user.dto.MailDto;
import gwangju.ssafy.backend.user.dto.UserDto;
import gwangju.ssafy.backend.user.exception.BadRequestException;
import gwangju.ssafy.backend.user.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@RequestMapping("user")
public class UserRestController {
    @Autowired
    private UserService userService;

    @PostMapping("/mail/send")
    public boolean sendMail(@RequestBody MailDto mailDto) {
        userService.sendSimpleMessage(mailDto);
        log.info("메일 전송 완료");
        return true;
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
