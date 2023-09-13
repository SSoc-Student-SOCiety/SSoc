package gwangju.ssafy.backend.domain.account.exception;


import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum AccountError {
	NOT_GROUP_MEMBER(HttpStatus.FORBIDDEN, "그룹원이 아닙니다."),
	NOT_GROUP_MANAGER(HttpStatus.FORBIDDEN, "그룹 관리자가 아닙니다."),
	NOT_EXISTS_TRANSACTION(HttpStatus.NOT_FOUND,"존재하지 않는 거래내역입니다."),
	NOT_EXISTS_ACCOUNT(HttpStatus.NOT_FOUND,"존재하지 않는 계좌입니다."),
	NOT_LINKED_ACCOUNT(HttpStatus.NOT_FOUND,"연결되지 않은 계좌입니다."),
	ALREADY_REGISTERED_ACCOUNT(HttpStatus.BAD_REQUEST,"이미 등록된 계좌입니다."),
	ALREADY_SENDED_AUTHCODE(HttpStatus.BAD_REQUEST,"이미 인증 코드가 발송되었습니다."),
	NOT_EXISTS_AUTHCODE(HttpStatus.NOT_FOUND,"존재하지 않는 인증코드 입니다."),
	NOT_MATCHED_AUTHCODE(HttpStatus.BAD_REQUEST,"인증코드가 일치하지 않습니다."),
	NOT_EXISTS_GROUP(HttpStatus.NOT_FOUND,"존재하지 않는 그룹입니다.");
	private final HttpStatus httpStatus;
	private final String errorMessage;
}
