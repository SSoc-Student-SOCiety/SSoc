package gwangju.ssafy.backend.global.exception;

import lombok.Getter;

@Getter
public class EmailException extends RuntimeException {
    private final ErrorCode errorCode;
    private final int status;
    private final String errorMessage;

    public EmailException(ErrorCode errorCode) {
        super(errorCode.getErrorMessage());
        this.errorCode = errorCode;
        this.status = errorCode.getHttpStatus().value();
        this.errorMessage = errorCode.getErrorMessage();
    }

}
