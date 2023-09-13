package gwangju.ssafy.backend.domain.account.exception;


import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum AccountError {
	NOT_GROUP_MEMBER(HttpStatus.FORBIDDEN, "그룹원이 아닙니다."),
	NOT_GROUP_MANAGER(HttpStatus.FORBIDDEN, "그룹 관리자가 아닙니다."),
	NOT_EXISTS_TRANSACTION(HttpStatus.NOT_FOUND,"존재하지 않는 스케줄입니다.");
	private final HttpStatus httpStatus;
	private final String errorMessage;
}
