package gwangju.ssafy.backend.domain.group.service.impl;

import gwangju.ssafy.backend.domain.group.dto.DeleteGroupMemberInfo;
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
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

import static gwangju.ssafy.backend.domain.group.exception.GroupError.NOT_GROUP_MANAGER;
import static gwangju.ssafy.backend.domain.group.exception.GroupError.NOT_GROUP_MEMBER;


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
	public List<GetGroupMemberInfo> getMemberList(Long loginMemberId, Long groupId) {
		GroupMember groupMember = groupMemberRepository.findByGroupIdAndUserId(groupId, loginMemberId)
				.orElseThrow(() -> new GroupException((NOT_GROUP_MEMBER)));

		// 해당 로그인한 유저가 그룹장이 아닌 경우
		if (groupMember.getRole() != GroupMemberRole.MANAGER) {
			throw new GroupException(NOT_GROUP_MANAGER);
		}

		// 로그인한 유저을 제외한 나머지 그룹원 리스트 조회
		List<GroupMember> groupMemberList = groupMemberRepository.findAllByGroupIdAndUserId(groupId, loginMemberId);
		List<GetGroupMemberInfo> groupMemberInfoList = new ArrayList<>();
		for(GroupMember tempGroupMember: groupMemberList) {
			// 비활성화 아닌(즉, false) 유저들만 그룹원 리스트에 넣어주기
			if(!tempGroupMember.getUser().isDeActivateCheck()) {
				GetGroupMemberInfo groupMemberInfo = new GetGroupMemberInfo();
				groupMemberInfo.convert(tempGroupMember);
				groupMemberInfoList.add(groupMemberInfo);
			}
		}

		return groupMemberInfoList;
	}

	// 그룹장이 해당 그룹원 삭제
	@Override
	public DeleteGroupMemberInfo deleteGroupMember(Long groupId, Long userId, Long loginMemberId) {
		// 그룹장 조회한 뒤
		GroupMember groupMember = groupMemberRepository.findByGroupIdAndUserId(groupId, loginMemberId)
				.orElseThrow(() -> new GroupException((NOT_GROUP_MEMBER)));

		// 그룹장이 아닌 경우
		if (groupMember.getRole() != GroupMemberRole.MANAGER) {
			throw new GroupException(NOT_GROUP_MANAGER);
		}

		// 해당 삭제할 유저 뽑아내기
		GroupMember deleteMember = groupMemberRepository.findByGroupIdAndUserId(groupId,
						userId)
				.orElseThrow(() -> new GroupException(GroupError.NOT_GROUP_MEMBER));
		groupMemberRepository.deleteById(deleteMember.getId());
		return DeleteGroupMemberInfo.builder()
				.groupMemberId(deleteMember.getId())
				.groupId(deleteMember.getGroup().getId())
				.groupName(deleteMember.getGroup().getName())
				.userId(deleteMember.getUser().getId())
				.userEmail(deleteMember.getUser().getUserEmail())
				.userName(deleteMember.getUser().getUserName())
				.userNickName(deleteMember.getUser().getUserNickname())
				.role(deleteMember.getRole())
				.build();
	}


}
