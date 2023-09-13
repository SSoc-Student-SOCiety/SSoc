package gwangju.ssafy.backend.domain.account.exception;

import lombok.Getter;

@Getter
public class AccountException extends RuntimeException{

	private final AccountError errorCode;

	public AccountException(AccountError errorCode) {
		super(errorCode.getErrorMessage());
		this.errorCode = errorCode;
	}

	public AccountException(AccountError errorCode, Throwable e) {
		super(errorCode.getErrorMessage(), e);
		this.errorCode =errorCode;
	}

}
