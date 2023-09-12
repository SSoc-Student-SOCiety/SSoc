package gwangju.ssafy.backend.domain.post.repository;

import gwangju.ssafy.backend.domain.post.dto.CommentInfo;
import gwangju.ssafy.backend.domain.post.dto.cond.SearchCommentCond;
import java.util.List;

public interface QuerydslCommentRepository {

	List<CommentInfo> findAllInPostByCond(Long postId, SearchCommentCond cond);

}
