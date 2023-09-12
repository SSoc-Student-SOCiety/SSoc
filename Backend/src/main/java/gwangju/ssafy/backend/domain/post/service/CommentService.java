package gwangju.ssafy.backend.domain.post.service;

import gwangju.ssafy.backend.domain.post.dto.CommentInfo;
import gwangju.ssafy.backend.domain.post.dto.CreateCommentRequest;
import gwangju.ssafy.backend.domain.post.dto.DeleteCommentRequest;
import gwangju.ssafy.backend.domain.post.dto.EditCommentRequest;
import gwangju.ssafy.backend.domain.post.dto.SearchCommentRequest;
import java.util.List;

public interface CommentService {
	Long createComment(CreateCommentRequest request);
	Long editComment(EditCommentRequest request);
	Long deleteComment(DeleteCommentRequest request);

	List<CommentInfo> searchComment(SearchCommentRequest request);
}
