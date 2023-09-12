package gwangju.ssafy.backend.domain.post.repository;

import gwangju.ssafy.backend.domain.post.dto.ReplyInfo;
import gwangju.ssafy.backend.domain.post.dto.cond.SearchReplyCond;
import java.util.List;

public interface QuerydslReplyRepository {

	List<ReplyInfo> findAllInCommentByCond(Long commentId, SearchReplyCond cond);

}
