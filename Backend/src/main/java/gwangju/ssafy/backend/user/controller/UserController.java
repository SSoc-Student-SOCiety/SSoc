package gwangju.ssafy.backend.user.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@Slf4j
@RequestMapping("user")
public class UserController {

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


}
