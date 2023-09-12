package gwangju.ssafy.backend.domain.board.service;

import gwangju.ssafy.backend.domain.board.dto.CreateCommentRequest;

public interface CommentService {
	Long createComment(CreateCommentRequest request);

}
