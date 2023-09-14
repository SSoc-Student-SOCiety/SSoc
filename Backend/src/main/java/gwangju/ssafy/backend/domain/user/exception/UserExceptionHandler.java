package gwangju.ssafy.backend.domain.user.exception;

import gwangju.ssafy.backend.global.common.dto.Message;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class UserExceptionHandler {

    @ExceptionHandler(UserException.class)
    public ResponseEntity<Message> userException(UserException e) {
        log.error("{} is occured", e.getMessage());
        if(e.getErrorCode().toString().equals("ALREADY_USER_LOGOUT")) {
            return ResponseEntity.ok().body(Message.success());
        }
        return ResponseEntity.ok().body(Message.fail(null, e.getMessage()));
    }
}
