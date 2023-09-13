package gwangju.ssafy.backend.domain.group.service.impl;

import gwangju.ssafy.backend.domain.group.dto.GetGroupMemberInfo;
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
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;


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

	// 로그인한 유저(그룹장)의 해당 그룹원 리스트 조회 (그룹장은 제외하고 나와야함)
	@Transactional(readOnly = true)
	@Override
	public List<GetGroupMemberInfo> getMemberList(Long userId, Long groupId) {
		List<GroupMember> groupMemberList = groupMemberRepository.findAllByGroupIdAndUserId(groupId, userId);
		List<GetGroupMemberInfo> groupMemberInfoList = new ArrayList<>();
		for(GroupMember tempGroupMember: groupMemberList) {
			GetGroupMemberInfo groupMemberInfo = new GetGroupMemberInfo();
			groupMemberInfo.convert(tempGroupMember);
			groupMemberInfoList.add(groupMemberInfo);
		}

		return groupMemberInfoList;
	}


}
