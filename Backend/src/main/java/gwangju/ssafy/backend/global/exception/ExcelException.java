package gwangju.ssafy.backend.global.exception;

import lombok.Getter;

@Getter
public class ExcelException extends RuntimeException {

    public ExcelException(String errorMessage) {
        super(errorMessage);
    }

}
