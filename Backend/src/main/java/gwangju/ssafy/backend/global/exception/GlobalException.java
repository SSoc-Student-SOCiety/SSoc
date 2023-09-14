package gwangju.ssafy.backend.global.exception;

import gwangju.ssafy.backend.global.common.dto.Message;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class GlobalException {
    @ExceptionHandler(TokenException.class)
    public ResponseEntity<Message> tokenExceptionHandler(TokenException e) {
        log.error("{} is occured", e.getMessage());
        return ResponseEntity.ok().body(Message.fail(null, e.getMessage()));
    }

    @ExceptionHandler(EmailException.class)
    public ResponseEntity<Message> emailException(EmailException e) {
        log.error("{} is occured", e.getMessage());
        return ResponseEntity.ok().body(Message.fail(null, e.getMessage()));
    }

    @ExceptionHandler(UserException.class)
    public ResponseEntity<Message> userException(UserException e) {
        log.error("{} is occured", e.getMessage());
        return ResponseEntity.ok().body(Message.fail(null, e.getMessage()));
    }

    @ExceptionHandler(ExcelException.class)
    public ResponseEntity<Message> excelException(ExcelException e) {
        log.error("{} is occured", e.getMessage());
        return ResponseEntity.ok().body(Message.fail(null, e.getMessage()));
    }



}
