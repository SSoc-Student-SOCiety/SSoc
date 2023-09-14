package gwangju.ssafy.backend.global.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum GlobalError {
    NOT_AUTHORITY_USER_API(HttpStatus.FORBIDDEN, "해당 API 호출에 대한 권한이 없습니다."),
    INVALID_TOKEN(HttpStatus.UNAUTHORIZED, "유효하지 않은 인증 토큰입니다."),
    EXPIRED_TOKEN(HttpStatus.UNAUTHORIZED, "AccessToken과 RefreshToken이 둘다 만료되었습니다."),
    ACCESS_EXPIRED_TOKEN(HttpStatus.UNAUTHORIZED, "RefreshToken은 살아있지만 AccessToekn이 만료되었습니다."),
    CERTIFICATION_NOT_TOKEN(HttpStatus.UNAUTHORIZED, "자격 증명이 되어 있지 않은 토큰입니다."),
    REDIS_NOT_TOKEN(HttpStatus.INTERNAL_SERVER_ERROR, "해당 Refresh 토큰이 Redis에 저장되어 있지 않아 재발급이 불가능합니다. 다시 로그인 해주세요."),
    NOT_VALIDATE_TOKEN(HttpStatus.UNAUTHORIZED, "해당 Refresh 토큰이 Redis에 저장된 토큰값과 동일하지 않습니다. 다시 로그인 해주세요."),
    NOT_SEND_EMAIL(HttpStatus.BAD_REQUEST, "설정 오류로 인해 이메일을 보낼 수 없습니다."),
    SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "서버 에러 입니다.");

    private final HttpStatus httpStatus;
    private final String errorMessage;
}
