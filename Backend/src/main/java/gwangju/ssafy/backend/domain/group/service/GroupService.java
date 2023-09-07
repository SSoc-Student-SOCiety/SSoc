package gwangju.ssafy.backend.domain.group.service;

import gwangju.ssafy.backend.domain.group.dto.CreateGroupRequest;
import gwangju.ssafy.backend.domain.group.dto.EditGroupInfoRequest;
import gwangju.ssafy.backend.domain.group.dto.GroupSearchCond;
import gwangju.ssafy.backend.domain.group.dto.GroupSimpleInfo;
import gwangju.ssafy.backend.domain.group.dto.MyGroupSearchCond;
import java.util.List;

public interface GroupService {

	void createGroup(CreateGroupRequest request);

	void inactiveGroup(Long groupId);

	void editGroupInfo(EditGroupInfoRequest request);

	List<GroupSimpleInfo> searchGroup(GroupSearchCond cond);
	List<GroupSimpleInfo> searchMyGroup(MyGroupSearchCond cond);
}
