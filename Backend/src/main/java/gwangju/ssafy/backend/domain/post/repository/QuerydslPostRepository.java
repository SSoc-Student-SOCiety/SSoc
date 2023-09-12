package gwangju.ssafy.backend.domain.post.repository;

import gwangju.ssafy.backend.domain.post.dto.PostInfo;
import gwangju.ssafy.backend.domain.post.dto.cond.SearchPostCond;
import java.util.List;

public interface QuerydslPostRepository {

	List<PostInfo> findAllInGroupByCond(Long groupId, SearchPostCond cond);

}
