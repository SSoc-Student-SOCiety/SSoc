package gwangju.ssafy.backend.domain.schedule.exception;

import lombok.Getter;

@Getter
public class ScheduleException extends RuntimeException{

	private final ScheduleError errorCode;

	public ScheduleException(ScheduleError errorCode) {
		super(errorCode.getErrorMessage());
		this.errorCode = errorCode;
	}

	public ScheduleException(ScheduleError errorCode, Throwable e) {
		super(errorCode.getErrorMessage(), e);
		this.errorCode =errorCode;
	}

}
