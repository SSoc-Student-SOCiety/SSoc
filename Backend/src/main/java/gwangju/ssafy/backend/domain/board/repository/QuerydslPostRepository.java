package gwangju.ssafy.backend.domain.board.repository;

import gwangju.ssafy.backend.domain.board.dto.PostInfo;
import gwangju.ssafy.backend.domain.board.dto.cond.SearchPostCond;
import java.util.List;

public interface QuerydslPostRepository {

	List<PostInfo> findAllInGroupByCond(Long groupId, SearchPostCond cond);

}
