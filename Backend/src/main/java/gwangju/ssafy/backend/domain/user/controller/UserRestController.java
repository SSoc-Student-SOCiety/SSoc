package gwangju.ssafy.backend.domain.user.controller;

import gwangju.ssafy.backend.domain.user.dto.*;
import gwangju.ssafy.backend.domain.user.service.UserService;
import gwangju.ssafy.backend.global.common.dto.*;
import gwangju.ssafy.backend.global.component.jwt.JwtUtils;
import gwangju.ssafy.backend.global.component.jwt.dto.TokenDto;
import gwangju.ssafy.backend.global.component.jwt.dto.TokenUserInfoDto;
import gwangju.ssafy.backend.global.component.jwt.security.JwtAuthenticationFilter;
import gwangju.ssafy.backend.global.component.jwt.service.JwtService;
import gwangju.ssafy.backend.global.exception.ErrorCode;
import gwangju.ssafy.backend.global.infra.email.EmailService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.parameters.P;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

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
        MailCodeDto mailCodeDto = null;
        mailCodeDto = emailService.sendSimpleMessage(userDto.getUserEmail(), true);
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
        TokenUserInfoDto tokenUserInfoDto = null;
        TokenDto tokenDto = null;
        UserLoginResponseDto userLoginResponseDto = null;
        try {
            tokenUserInfoDto = userService.loginCheckUser(userLoginRequestDto);
            tokenDto = jwtService.issueToken(tokenUserInfoDto);
            userLoginResponseDto = UserLoginResponseDto.builder()
                    .userInfo(tokenUserInfoDto)
                    .token(tokenDto)
                    .build();

        } catch (RuntimeException e) {
            return ResponseEntity.ok().body(Message.fail(null, e.getMessage()));
        }
        return ResponseEntity.ok().body(Message.success(userLoginResponseDto));
    }

    // 로그아웃 -> 토큰 필요
    @PostMapping("/logout")
    public ResponseEntity<Message> logout() {
        Authentication authentication = null;
        LoginActiveUserDto loginActiveUserDto = null;
        try {
            authentication = SecurityContextHolder.getContext().getAuthentication();
            loginActiveUserDto = (LoginActiveUserDto) authentication.getPrincipal();
        }
        catch(Exception e) {
            return ResponseEntity.ok().body(Message.fail(null, ErrorCode.INVALID_TOKEN.getErrorMessage()));
        }
        jwtService.deleteRefreshToken(loginActiveUserDto.getUserEmail());
        return ResponseEntity.ok().body(Message.success());
    }

    // JWT 토큰 재발급
//    @PostMapping("/reissue")
//    public ResponseEntity<Message<TokenDto>> reissue(@RequestBody TokenRequestDto tokenRequestDto) {
//        log.info("==================JWT 토큰 재발급 진입=============");
//        TokenDto tokenDto = userService.reissue(tokenRequestDto);
//        return ResponseEntity.ok().body(Message.success(tokenDto));
////        return ResponseEntity.ok().body(userService.reissue(tokenRequestDto));
//    }

    // 닉네임 수정 -> 토큰 필요
    @PostMapping("/update/nickname")
    public ResponseEntity<Message<UserLoginResponseDto>> updateNickName(@RequestBody UserUpdateDto userUpdateDto) {
        log.info("==================회원정보 닉네임 수정 Controller 진입=============");
        Authentication authentication = null;
        LoginActiveUserDto loginActiveUserDto = null;
        try {
            authentication = SecurityContextHolder.getContext().getAuthentication();
            loginActiveUserDto = (LoginActiveUserDto) authentication.getPrincipal();
        }
        catch(Exception e) {
            return ResponseEntity.ok().body(Message.fail(null, ErrorCode.INVALID_TOKEN.getErrorMessage()));
        }
        userService.updateNickName(userUpdateDto);  // 닉네임 최신화
        TokenUserInfoDto tokenUserInfoDto = userService.userInformationFind(loginActiveUserDto.getUserEmail());
        TokenDto tokenDto = jwtService.issueToken(tokenUserInfoDto);
        UserLoginResponseDto userLoginResponseDto = UserLoginResponseDto.builder()
                .userInfo(tokenUserInfoDto)
                .token(tokenDto)
                .build();
        return ResponseEntity.ok().body(Message.success(userLoginResponseDto));
    }

    // 비밀번호 수정 -> 토큰 필요
    @PostMapping("/update/password")
    public ResponseEntity<Message<UserLoginResponseDto>> updatePassword(@RequestBody UserUpdateDto userUpdateDto) {
        log.info("==================회원정보 비밀번호 수정 Controller 진입=============");
        Authentication authentication = null;
        LoginActiveUserDto loginActiveUserDto = null;
        try {
            authentication = SecurityContextHolder.getContext().getAuthentication();
            loginActiveUserDto = (LoginActiveUserDto) authentication.getPrincipal();
        }
        catch(Exception e) {
            return ResponseEntity.ok().body(Message.fail(null, ErrorCode.INVALID_TOKEN.getErrorMessage()));
        }

        int code = userService.updatePassword(userUpdateDto);
        if(code == 0) {
            TokenUserInfoDto tokenUserInfoDto = userService.userInformationFind(loginActiveUserDto.getUserEmail());
            TokenDto tokenDto = jwtService.issueToken(tokenUserInfoDto);
            UserLoginResponseDto userLoginResponseDto = UserLoginResponseDto.builder()
                    .userInfo(tokenUserInfoDto)
                    .token(tokenDto)
                    .build();
            return ResponseEntity.ok().body(Message.success(userLoginResponseDto));
        }
        else if(code == 1) {
            return ResponseEntity.ok().body(Message.fail("1", "현재 비밀번호를 잘못 입력하였습니다."));
        }
        else {
            return ResponseEntity.ok().body(Message.fail("2", "현재 비밀번호가 변경하려는 비밀번호와 같습니다."));
        }
    }

    // 프로필 이미지 수정 -> 토큰 필요
    @PostMapping("/update/image")
    public ResponseEntity<Message<UserLoginResponseDto>> updateImage(@RequestBody UserUpdateDto userUpdateDto) {
        log.info("==================회원정보 프로필 이미지 수정 Controller 진입=============");
        Authentication authentication = null;
        LoginActiveUserDto loginActiveUserDto = null;
        try {
            authentication = SecurityContextHolder.getContext().getAuthentication();
            loginActiveUserDto = (LoginActiveUserDto) authentication.getPrincipal();
        }
        catch(Exception e) {
            return ResponseEntity.ok().body(Message.fail(null, ErrorCode.INVALID_TOKEN.getErrorMessage()));
        }

        userService.updateImage(userUpdateDto);
        TokenUserInfoDto tokenUserInfoDto = userService.userInformationFind(loginActiveUserDto.getUserEmail());
        TokenDto tokenDto = jwtService.issueToken(tokenUserInfoDto);
        UserLoginResponseDto userLoginResponseDto = UserLoginResponseDto.builder()
                .userInfo(tokenUserInfoDto)
                .token(tokenDto)
                .build();
        return ResponseEntity.ok().body(Message.success(userLoginResponseDto));
    }

    // 닉네임, 패스워드 수정 -> 토큰 필요
    @PostMapping("/update/nickname/password")
    public ResponseEntity<Message<UserLoginResponseDto>> updateNickNameAndPassword(@RequestBody UserUpdateDto userUpdateDto, HttpServletRequest request) {
        log.info("==================회원정보 닉네임, 비밀번호 수정 Controller 진입=============");
        Authentication authentication = null;
        LoginActiveUserDto loginActiveUserDto = null;
        try {
            authentication = SecurityContextHolder.getContext().getAuthentication();
            loginActiveUserDto = (LoginActiveUserDto) authentication.getPrincipal();
        }
        catch(Exception e) {
            // accessToken 만료 된 경우
            String refreshToken = request.getHeader(JwtAuthenticationFilter.REFRESH_HEADER);
            if(StringUtils.hasText(refreshToken) && refreshToken.startsWith(JwtAuthenticationFilter.BEARER_PREFIX)) {
                refreshToken = refreshToken.substring(7);
                TokenDto tokenDto = null;
                try {
                    tokenDto = jwtService.refreshToken(refreshToken);
                }
                catch(Exception refreshTokenError) {
                    // refresh토큰까지 만료됐다고 에러 코드 발생
                    return ResponseEntity.ok().body(Message.fail(null, ErrorCode.INVALID_TOKEN.getErrorMessage()));
                }
                int code = userService.updatePassword(userUpdateDto);
                if(code == 0) {
                    userService.updateNickName(userUpdateDto);
                    TokenUserInfoDto tokenUserInfoDto = userService.userInformationFind(loginActiveUserDto.getUserEmail());
                    UserLoginResponseDto userLoginResponseDto = UserLoginResponseDto.builder()
                            .userInfo(tokenUserInfoDto)
                            .token(tokenDto)
                            .build();
                    return ResponseEntity.ok().body(Message.success(userLoginResponseDto));
                }
                else if(code == 1) {
                    return ResponseEntity.ok().body(Message.fail("1", "현재 비밀번호를 잘못 입력하였습니다."));
                }
                else {
                    return ResponseEntity.ok().body(Message.fail("2", "현재 비밀번호가 변경하려는 비밀번호와 같습니다."));
                }
            }
        }
        int code = userService.updatePassword(userUpdateDto);
        if(code == 0) {
            userService.updateNickName(userUpdateDto);
            TokenUserInfoDto tokenUserInfoDto = userService.userInformationFind(loginActiveUserDto.getUserEmail());
            TokenDto tokenDto = jwtService.issueToken(tokenUserInfoDto);
            UserLoginResponseDto userLoginResponseDto = UserLoginResponseDto.builder()
                    .userInfo(tokenUserInfoDto)
                    .token(tokenDto)
                    .build();
            return ResponseEntity.ok().body(Message.success(userLoginResponseDto));
        }
        else if(code == 1) {
            return ResponseEntity.ok().body(Message.fail("1", "현재 비밀번호를 잘못 입력하였습니다."));
        }
        else {
            return ResponseEntity.ok().body(Message.fail("2", "현재 비밀번호가 변경하려는 비밀번호와 같습니다."));
        }


    }

    // 토큰값들 살아있는지 체크
//    @PostMapping("/token/check")
//    public ResponseEntity<Message<TokenResponseDto>> tokenCheck(@RequestBody TokenRequestDto tokenRequestDto) {
//        log.info("==================토큰값 받아오기(Parser) Controller 진입=============");
//        TokenResponseDto tokenResponseDto = userService.tokenCheck(tokenRequestDto);
//        if(tokenResponseDto.getCode() == 0) {
//            return ResponseEntity.ok().body(Message.success(tokenResponseDto));
//        }
//        return ResponseEntity.ok().body(Message.fail(null, ""));
//    }

    // 임시 비밀번호 재발급 -> 토큰 필요 없음
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

    // 회원정보 불러오기 (앱 처음 구동 시), (마이페이지때도 사용 가능할 듯) -> 토큰 필요
    @PostMapping("/info")
    public ResponseEntity<Message<UserLoginResponseDto>> userInformationFind(HttpServletRequest request) {
        log.info("==================회원 정보 불러오기 Controller 진입=============");
        Authentication authentication = null;
        LoginActiveUserDto loginActiveUserDto = null;
        TokenUserInfoDto tokenUserInfoDto = null;
        TokenDto tokenDto = null;
        try {
            authentication = SecurityContextHolder.getContext().getAuthentication();
            loginActiveUserDto = (LoginActiveUserDto) authentication.getPrincipal();
        }
        catch(Exception e) {
            String bearerToken = request.getHeader(JwtAuthenticationFilter.REFRESH_HEADER);
            UserLoginResponseDto userLoginResponseDto = null;
            if (StringUtils.hasText(bearerToken) && bearerToken.startsWith(bearerToken)) {
                String jwt = bearerToken.substring(7);

                try {
                    Jws<Claims> claims = jwtService.getClaims(jwt);
                    String userEmail = claims.getBody().get("userEmail").toString();
                    Optional<String> refreshToken = jwtService.find(userEmail);
                    tokenUserInfoDto = userService.userInformationFind(userEmail);
                    tokenDto = jwtService.issueToken(tokenUserInfoDto);
                    userLoginResponseDto = UserLoginResponseDto.builder()
                            .userInfo(tokenUserInfoDto)
                            .token(tokenDto)
                            .build();
                }
                catch(Exception e2){
                    return ResponseEntity.ok().body(Message.fail("1", "Refresh Token이 만료 되었습니다."));
                }
            }
            return ResponseEntity.ok().body(Message.success(userLoginResponseDto, "1", "Access Token이 만료되었습니다. Access Token과 Refresh Toekn을 갱신하겠습니다"));

        }
        tokenUserInfoDto = userService.userInformationFind(loginActiveUserDto.getUserEmail());
        tokenDto = jwtService.issueToken(tokenUserInfoDto);
        UserLoginResponseDto userLoginResponseDto = UserLoginResponseDto.builder()
                .userInfo(tokenUserInfoDto)
                .token(tokenDto)
                .build();
        return ResponseEntity.ok().body(Message.success(userLoginResponseDto));
    }


}
