package gwangju.ssafy.backend.domain.group.exception;


import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum GroupError {
	NOT_EXISTS_SCHOOL(HttpStatus.BAD_REQUEST, "존재하지 않는 학교입니다."),
	NOT_EXISTS_GROUP(HttpStatus.BAD_REQUEST, "존재하지 않는 그룹입니다."),
	NOT_GROUP_MANAGER(HttpStatus.FORBIDDEN, "그룹 관리자가 아닙니다."),
	NOT_GROUP_MEMBER(HttpStatus.FORBIDDEN, "그룹원이 아닙니다."),
	NOT_EXISTS_USER(HttpStatus.BAD_REQUEST, "존재하지 않는 회원입니다.");

	private final HttpStatus httpStatus;
	private final String errorMessage;
}
