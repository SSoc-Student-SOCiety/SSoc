package gwangju.ssafy.backend.domain.group.exception;

import gwangju.ssafy.backend.global.common.dto.Message;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class GroupExceptionHandler {

	@ExceptionHandler(GroupException.class)
	public ResponseEntity<Message> groupExceptionHandler(GroupException e) {
		log.error("{} is occured", e.getErrorCode());
		return ResponseEntity.status(e.getErrorCode().getHttpStatus())
			.body(Message.fail(e.getErrorCode().name(), e.getErrorCode().getErrorMessage()));
	}
}
