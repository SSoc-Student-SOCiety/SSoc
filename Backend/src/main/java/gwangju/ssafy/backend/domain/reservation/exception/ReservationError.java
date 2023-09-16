package gwangju.ssafy.backend.domain.reservation.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum ReservationError {
    NOT_EXIST_PRODUCT(HttpStatus.BAD_REQUEST, "해당 품목이 존재하지 않습니다"),
    NOT_EXIST_RESERVATION(HttpStatus.BAD_REQUEST, "해당 예약 내역이 없습니다.");

    private final HttpStatus httpStatus;
    private final String errorMessage;
}
