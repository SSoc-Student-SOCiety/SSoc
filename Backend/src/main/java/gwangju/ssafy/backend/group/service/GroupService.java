package gwangju.ssafy.backend.group.service;

import gwangju.ssafy.backend.group.dto.CreateGroupRequest;

public interface GroupService {

	void createGroup(CreateGroupRequest request);

	void inactiveGroup(Long groupId);

}
