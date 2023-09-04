package gwangju.ssafy.backend.domain.group.service;

import gwangju.ssafy.backend.domain.group.entity.enums.GroupMemberRole;

public interface GroupMemberService {

	void addMemberInGroup(Long groupId, Long userId, GroupMemberRole role);

}
