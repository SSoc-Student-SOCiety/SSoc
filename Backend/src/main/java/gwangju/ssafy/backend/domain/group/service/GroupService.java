package gwangju.ssafy.backend.domain.group.service;

import gwangju.ssafy.backend.domain.group.dto.CreateGroupRequest;
import gwangju.ssafy.backend.domain.group.dto.EditGroupInfoRequest;

public interface GroupService {

	void createGroup(CreateGroupRequest request);

	void inactiveGroup(Long groupId);

	void editGroupInfo(EditGroupInfoRequest request);
}
