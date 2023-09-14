package gwangju.ssafy.backend.domain.user.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum UserError {
    NOT_FOUND_USER(HttpStatus.INTERNAL_SERVER_ERROR, "해당 이메일을 가진 회원을 찾을 수 없습니다."),
    NOT_EXIST_USER(HttpStatus.INTERNAL_SERVER_ERROR, "DB내에 해당 유저가 존재하지 않습니다."),
    NOT_MATCH_PASSWORD(HttpStatus.BAD_REQUEST, "비밀번호가 일치하지 않습니다."),
    CURRENT_CHANGE_MATCH_PASSWORD(HttpStatus.BAD_REQUEST, "현재 비밀번호가 변경하려는 비밀번호와 같습니다."),
    EXIST_USER_EMAIL(HttpStatus.BAD_REQUEST, "이미 가입되어 있는 이메일입니다."),
    ALREADY_USER_LOGOUT(HttpStatus.BAD_REQUEST, "RefreshToken이 redis에 저장되어 있지 않기 때문에 이미 로그아웃 된 유저입니다.");

    private final HttpStatus httpStatus;
    private final String errorMessage;
}
