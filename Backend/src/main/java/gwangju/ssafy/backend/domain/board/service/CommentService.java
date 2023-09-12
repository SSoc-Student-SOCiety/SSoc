package gwangju.ssafy.backend.domain.board.service;

import gwangju.ssafy.backend.domain.board.dto.CreateCommentRequest;
import gwangju.ssafy.backend.domain.board.dto.EditCommentRequest;

public interface CommentService {
	Long createComment(CreateCommentRequest request);
	Long editComment(EditCommentRequest request);

}
