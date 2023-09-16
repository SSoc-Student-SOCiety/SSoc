package gwangju.ssafy.backend.domain.reservation.exception;

import lombok.Getter;

@Getter
public class ReservationException extends RuntimeException {
    private final ReservationError errorCode;
    private final int status;
    private final String errorMessage;

    public ReservationException(ReservationError errorCode) {
        super(errorCode.getErrorMessage());
        this.errorCode = errorCode;
        this.status = errorCode.getHttpStatus().value();
        this.errorMessage = errorCode.getErrorMessage();
    }

    public ReservationException(ReservationError errorCode, Throwable e) {
        super(errorCode.getErrorMessage(), e);
        this.errorCode = errorCode;
        this.status = errorCode.getHttpStatus().value();
        this.errorMessage = errorCode.getErrorMessage();
    }
}
