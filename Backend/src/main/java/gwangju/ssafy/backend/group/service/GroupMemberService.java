package gwangju.ssafy.backend.group.service;

import gwangju.ssafy.backend.group.entity.enums.GroupMemberRole;

public interface GroupMemberService {

	void addMemberInGroup(Long groupId, Long userId, GroupMemberRole role);

}
