package gwangju.ssafy.backend.domain.group.exception;

import lombok.Getter;

@Getter
public class GroupException extends RuntimeException{

	private final GroupError errorCode;

	public GroupException(GroupError errorCode) {
		super(errorCode.getErrorMessage());
		this.errorCode = errorCode;
	}

	public GroupException(GroupError errorCode, Throwable e) {
		super(errorCode.getErrorMessage(), e);
		this.errorCode =errorCode;
	}

}
