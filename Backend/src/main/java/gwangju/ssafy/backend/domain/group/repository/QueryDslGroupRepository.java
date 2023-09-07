package gwangju.ssafy.backend.domain.group.repository;

import gwangju.ssafy.backend.domain.group.dto.GroupSearchCond;
import gwangju.ssafy.backend.domain.group.dto.GroupSimpleInfo;
import java.util.List;

public interface QueryDslGroupRepository {

	// 그룹 필터링 및 페이징 조회
	public List<GroupSimpleInfo> findAllBySearchCond(GroupSearchCond cond);

}
