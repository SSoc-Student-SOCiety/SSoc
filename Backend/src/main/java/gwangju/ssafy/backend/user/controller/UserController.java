package gwangju.ssafy.backend.user.controller;

import gwangju.ssafy.backend.user.dto.MailDto;
import gwangju.ssafy.backend.user.exception.BadRequestException;
import gwangju.ssafy.backend.user.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@Slf4j
@RequestMapping("user")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/mailform")
    public String mailForm() {
        log.info("==================mailForm view controller 진입=============");
        return "user/mailform";
    }


    @PostMapping("/mail/send")
    public boolean sendMail(MailDto mailDto) {
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



}
