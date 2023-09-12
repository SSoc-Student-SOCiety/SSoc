package gwangju.ssafy.backend.domain.user.controller;

import gwangju.ssafy.backend.domain.user.dto.*;
import gwangju.ssafy.backend.domain.user.service.UserService;
import gwangju.ssafy.backend.global.common.dto.*;
import gwangju.ssafy.backend.global.component.jwt.dto.TokenDto;
import gwangju.ssafy.backend.global.component.jwt.dto.TokenUserInfoDto;
import gwangju.ssafy.backend.global.component.jwt.service.JwtService;
import gwangju.ssafy.backend.global.infra.email.EmailService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;


@RestController
@Slf4j
@RequestMapping("user")
public class UserRestController {
    @Autowired
    private UserService userService;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private EmailService emailService;

    // 이메일을 통한 인증코드 보내기 -> 토큰 필요 없음
    @PostMapping("/email/send")
    public ResponseEntity<Message<MailCodeDto>> sendMail(@RequestBody UserDto userDto) throws Exception {
        MailCodeDto mailCodeDto = emailService.sendSimpleMessage(userDto.getUserEmail(), true);
        log.info("메일 전송 완료");
        return ResponseEntity.ok().body(Message.success(mailCodeDto));
    }

    // 이메일 중복 체크 -> 토큰 필요 없음
    @PostMapping("/email/check")
    public ResponseEntity<Message> checkEmailDuplication(@RequestBody UserDto userDto) {
        log.info("==================이메일 중복체크 진입=============");
        userService.existsUserByUserEmail(userDto.getUserEmail());
        return ResponseEntity.ok().body(Message.success());
    }

    // 회원가입 -> 토큰 필요 없음
    @PostMapping("/signup")
    public ResponseEntity<Message> signUp(@RequestBody @Valid UserSignUpRequestDto userSignUpRequestDto) {
        log.info("==================회원가입 진입=============");
        userService.signUpUser(userSignUpRequestDto);
        return ResponseEntity.ok().body(Message.success());
    }

    // 로그인 -> 토큰 필요 없음
    @PostMapping("/login")
    public ResponseEntity<Message<UserLoginResponseDto>> login(@RequestBody UserLoginRequestDto userLoginRequestDto) {
        log.info("==================로그인 진입=============");
        TokenUserInfoDto tokenUserInfoDto = userService.loginCheckUser(userLoginRequestDto);
        TokenDto tokenDto = jwtService.issueToken(tokenUserInfoDto);
        UserLoginResponseDto userLoginResponseDto = UserLoginResponseDto.builder()
                .userInfo(tokenUserInfoDto)
                .token(tokenDto)
                .build();
        return ResponseEntity.ok().body(Message.success(userLoginResponseDto));
    }

    // 로그아웃 -> 토큰 필요
    @PostMapping("/logout")
    @PreAuthorize("hasAuthority('USER') or hasAuthority('ADMIN')")
    public ResponseEntity<Message> logout(@AuthenticationPrincipal LoginActiveUserDto loginActiveUserDto) {
        log.info("==================로그아웃 진입=============");
        jwtService.deleteRefreshToken(loginActiveUserDto.getUserEmail());
        return ResponseEntity.ok().body(Message.success());
    }

    // 닉네임 수정 -> 토큰 필요
    @PostMapping("/update/nickname")
    @PreAuthorize("hasAuthority('USER') or hasAuthority('ADMIN')")
    public ResponseEntity<Message<TokenUserInfoDto>> updateNickName(@RequestBody UserUpdateDto userUpdateDto) {
        log.info("==================회원정보 닉네임 수정 Controller 진입=============");
        TokenUserInfoDto tokenUserInfoDto = userService.updateNickName(userUpdateDto);  // 닉네임 최신화
        return ResponseEntity.ok().body(Message.success(tokenUserInfoDto));
    }

    // 비밀번호 수정 -> 토큰 필요
    @PostMapping("/update/password")
    @PreAuthorize("hasAuthority('USER') or hasAuthority('ADMIN')")
    public ResponseEntity<Message<TokenUserInfoDto>> updatePassword(@RequestBody UserUpdateDto userUpdateDto) {
        log.info("==================회원정보 비밀번호 수정 Controller 진입=============");
        TokenUserInfoDto tokenUserInfoDto = userService.updatePassword(userUpdateDto);
        return ResponseEntity.ok().body(Message.success(tokenUserInfoDto));
    }

    // 프로필 이미지 수정 -> 토큰 필요
    @PostMapping("/update/image")
    @PreAuthorize("hasAuthority('USER') or hasAuthority('ADMIN')")
    public ResponseEntity<Message<TokenUserInfoDto>> updateImage(@RequestBody UserUpdateDto userUpdateDto) {
        log.info("==================회원정보 프로필 이미지 수정 Controller 진입=============");
        TokenUserInfoDto tokenUserInfoDto = userService.updateImage(userUpdateDto);
        return ResponseEntity.ok().body(Message.success(tokenUserInfoDto));
    }

    // 닉네임, 패스워드 수정 -> 토큰 필요
    @PostMapping("/update/nickname/password")
    @PreAuthorize("hasAuthority('USER') or hasAuthority('ADMIN')")
    public ResponseEntity<Message<TokenUserInfoDto>> updateNickNameAndPassword(@RequestBody UserUpdateDto userUpdateDto, HttpServletRequest request) {
        log.info("==================회원정보 닉네임, 비밀번호 수정 Controller 진입=============");
        userService.updateNickName(userUpdateDto);
        TokenUserInfoDto tokenUserInfoDto = userService.updatePassword(userUpdateDto);
        return ResponseEntity.ok().body(Message.success(tokenUserInfoDto));
    }

    // 임시 비밀번호 재발급 -> 토큰 필요 없음
    @PostMapping("/email/password")
    public ResponseEntity<Message> sendTempPassword(@RequestBody UserDto userDto) throws Exception {
        MailCodeDto mailCodeDto = emailService.sendSimpleMessage(userDto.getUserEmail(), false);
        log.info("메일 전송 완료");
        userService.tempPassword(userDto, mailCodeDto);
        log.info("임시 비밀번호 발급 완료");
        return ResponseEntity.ok().body(Message.success());
    }

    // 앱 구동 시 회원 정보 불러오기
    @PostMapping("/start")
    @PreAuthorize("hasAuthority('USER') or hasAuthority('ADMIN')")
    public ResponseEntity<Message<LoginActiveUserDto>> userAppStart(
            @AuthenticationPrincipal LoginActiveUserDto loginActiveUserDto) {
        log.info("==================앱 처음 구동시 회원정보 Controller 진입=============");
        return ResponseEntity.ok().body(Message.success(loginActiveUserDto, "1", null));
    }

    // 회원 탈퇴
    @DeleteMapping("/delete")
    @PreAuthorize("hasAuthority('USER')")
    public ResponseEntity<Message> deleteUser() {
        log.info("==================회원 탈퇴 Controller 진입=============");
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        LoginActiveUserDto loginActiveUserDto = (LoginActiveUserDto) authentication.getPrincipal();
        userService.deleteUser(loginActiveUserDto.getId());
        return ResponseEntity.ok().body(Message.success(loginActiveUserDto));   // 삭제한 유저정보 반환
    }

    // 회원 정보 불러오기
//    @PostMapping("/info")
//    @PreAuthorize("hasAuthority('USER')")
//    public ResponseEntity<Message<LoginActiveUserDto>> userInfo() {
//        log.info("==================회원 정보 불러오기 Controller 진입=============");
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        LoginActiveUserDto loginActiveUserDto = (LoginActiveUserDto) authentication.getPrincipal();
//        return ResponseEntity.ok().body(Message.success(loginActiveUserDto, "1", null));
//    }


}
