package gwangju.ssafy.backend.group.service;

import gwangju.ssafy.backend.group.dto.CreateGroupRequest;
import gwangju.ssafy.backend.group.dto.EditGroupInfoRequest;

public interface GroupService {

	void createGroup(CreateGroupRequest request);

	void inactiveGroup(Long groupId);

	void editGroupInfo(EditGroupInfoRequest request);
}
