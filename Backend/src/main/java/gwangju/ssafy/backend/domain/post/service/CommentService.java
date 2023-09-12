package gwangju.ssafy.backend.domain.post.service;

import gwangju.ssafy.backend.domain.post.dto.CreateCommentRequest;
import gwangju.ssafy.backend.domain.post.dto.DeleteCommentRequest;
import gwangju.ssafy.backend.domain.post.dto.EditCommentRequest;

public interface CommentService {
	Long createComment(CreateCommentRequest request);
	Long editComment(EditCommentRequest request);
	Long deleteComment(DeleteCommentRequest request);

	List searchComment(SearchCommentRequest request);
}
