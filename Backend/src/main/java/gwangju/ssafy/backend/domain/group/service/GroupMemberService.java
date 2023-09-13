package gwangju.ssafy.backend.domain.group.service;

import gwangju.ssafy.backend.domain.group.dto.GetGroupMemberInfo;
import gwangju.ssafy.backend.domain.group.dto.GetMemberRoleRequest;
import gwangju.ssafy.backend.domain.group.dto.GetMemberRoleResponse;
import gwangju.ssafy.backend.domain.group.entity.enums.GroupMemberRole;

import java.util.List;

public interface GroupMemberService {

	void addMemberInGroup(Long groupId, Long userId, GroupMemberRole role);

	GetMemberRoleResponse getMemberRole(GetMemberRoleRequest request);

	List<GetGroupMemberInfo> getMemberList(Long userId, Long groupId);

}
