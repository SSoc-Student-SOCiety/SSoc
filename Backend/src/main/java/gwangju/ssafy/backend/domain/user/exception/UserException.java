package gwangju.ssafy.backend.domain.user.exception;

import lombok.Getter;

@Getter
public class UserException extends RuntimeException {
    private final UserError errorCode;
    private final int status;
    private final String errorMessage;


    public UserException(UserError errorCode) {
        super(errorCode.getErrorMessage());
        this.errorCode = errorCode;
        this.status = errorCode.getHttpStatus().value();
        this.errorMessage = errorCode.getErrorMessage();
    }

    public UserException(UserError errorCode, Throwable e) {
        super(errorCode.getErrorMessage(), e);
        this.errorCode =errorCode;
        this.status = errorCode.getHttpStatus().value();
        this.errorMessage = errorCode.getErrorMessage();
    }

}
