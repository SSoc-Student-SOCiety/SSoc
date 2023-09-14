package gwangju.ssafy.backend.global.exception;

import lombok.Getter;

@Getter
public class EmailException extends RuntimeException {
    private final GlobalError errorCode;
    private final int status;
    private final String errorMessage;

    public EmailException(GlobalError errorCode) {
        super(errorCode.getErrorMessage());
        this.errorCode = errorCode;
        this.status = errorCode.getHttpStatus().value();
        this.errorMessage = errorCode.getErrorMessage();
    }

}
