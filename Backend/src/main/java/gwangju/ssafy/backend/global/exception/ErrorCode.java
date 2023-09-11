package gwangju.ssafy.backend.global.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum ErrorCode {
    NOT_AUTHORITY_USER_API(HttpStatus.FORBIDDEN, "해당 API 호출에 대한 권한이 없습니다."),
    NOT_FOUND_USER(HttpStatus.BAD_REQUEST, "회원을 찾을 수 없습니다."),
    EXIST_USER_EMAIL(HttpStatus.BAD_REQUEST, "이미 가입되어 있는 이메일입니다."),
    INVALID_TOKEN(HttpStatus.UNAUTHORIZED, "유효하지 않은 인증 토큰입니다."),
    EXPIRED_TOKEN(HttpStatus.UNAUTHORIZED, "AccessToken과 RefreshToken이 둘다 만료되었습니다."),
    ACCESS_EXPIRED_TOKEN(HttpStatus.UNAUTHORIZED, "RefreshToken은 살아있지만 AccessToekn이 만료되었습니다."),
    NOT_SEND_EMAIL(HttpStatus.BAD_REQUEST, "설정 오류로 인해 이메일을 보낼 수 없습니다."),
    SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "서버 에러 입니다.");

    private final HttpStatus httpStatus;
    private final String errorMessage;
}
