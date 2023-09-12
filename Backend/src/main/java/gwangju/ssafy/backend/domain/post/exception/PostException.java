package gwangju.ssafy.backend.domain.post.exception;

import lombok.Getter;

@Getter
public class PostException extends RuntimeException{

	private final PostError errorCode;

	public PostException(PostError errorCode) {
		super(errorCode.getErrorMessage());
		this.errorCode = errorCode;
	}

	public PostException(PostError errorCode, Throwable e) {
		super(errorCode.getErrorMessage(), e);
		this.errorCode =errorCode;
	}

}
