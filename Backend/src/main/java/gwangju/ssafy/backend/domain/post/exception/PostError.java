package gwangju.ssafy.backend.domain.post.exception;


import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum PostError {
	NOT_GROUP_MEMBER(HttpStatus.FORBIDDEN, "그룹원이 아닙니다."),
	NOT_GROUP_MANAGER(HttpStatus.FORBIDDEN, "그룹 관리자가 아닙니다."),
	ALREADY_DELETED_POST(HttpStatus.NOT_FOUND, "삭제된 게시글 입니다."),
	NOT_EXISTS_POST(HttpStatus.NOT_FOUND,"존재하지 않는 게시글입니다."),
	NOT_POST_OWNER(HttpStatus.FORBIDDEN, "게시글 작성자가 아닙니다.");

	private final HttpStatus httpStatus;
	private final String errorMessage;
}
