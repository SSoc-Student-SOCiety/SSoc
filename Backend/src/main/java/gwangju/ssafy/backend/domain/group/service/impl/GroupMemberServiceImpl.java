package gwangju.ssafy.backend.domain.group.service.impl;

import gwangju.ssafy.backend.domain.group.dto.GetMemberRoleRequest;
import gwangju.ssafy.backend.domain.group.dto.GetMemberRoleResponse;
import gwangju.ssafy.backend.domain.group.entity.Group;
import gwangju.ssafy.backend.domain.group.entity.GroupMember;
import gwangju.ssafy.backend.domain.group.entity.enums.GroupMemberRole;
import gwangju.ssafy.backend.domain.group.exception.GroupError;
import gwangju.ssafy.backend.domain.group.exception.GroupException;
import gwangju.ssafy.backend.domain.group.repository.GroupMemberRepository;
import gwangju.ssafy.backend.domain.group.repository.GroupRepository;
import gwangju.ssafy.backend.domain.group.service.GroupMemberService;
import gwangju.ssafy.backend.domain.user.entity.User;
import gwangju.ssafy.backend.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@RequiredArgsConstructor
@Transactional
@Service
class GroupMemberServiceImpl implements GroupMemberService {

	private final GroupMemberRepository groupMemberRepository;
	private final UserRepository userRepository;
	private final GroupRepository groupRepository;

	@Override
	public void addMemberInGroup(Long groupId, Long userId, GroupMemberRole role) {

		User user = userRepository.findById(userId)
			.orElseThrow(() -> new GroupException(GroupError.NOT_EXISTS_USER));
		Group group = groupRepository.findByIdAndIsActiveIsTrue(groupId)
			.orElseThrow(() -> new GroupException(GroupError.NOT_EXISTS_GROUP));

		groupMemberRepository.save(GroupMember.builder()
			.group(group)
			.user(user)
			.role(role)
			.build());
	}

	@Transactional(readOnly = true)
	@Override
	public GetMemberRoleResponse getMemberRole(GetMemberRoleRequest request) {

		GroupMember member = groupMemberRepository.findByGroupIdAndUserId(request.getGroupId(),
				request.getUserId())
			.orElseThrow(() -> new GroupException(GroupError.NOT_GROUP_MEMBER));

		return GetMemberRoleResponse.of(member);
	}


}
