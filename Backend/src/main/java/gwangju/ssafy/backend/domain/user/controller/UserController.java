package gwangju.ssafy.backend.domain.user.controller;

import gwangju.ssafy.backend.domain.user.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

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

    // 회원 가입 폼으로 이동
    @GetMapping("/join")
    public String join() {
        log.info("==================joinform view controller 진입=============");
        return "user/joinform";
    }

    // 로그인 폼으로 이동
    @GetMapping("/loginform")
    public String login() {
        log.info("==================loginform view controller 진입=============");
        return "user/loginform";
    }

}
