package gwangju.ssafy.backend.domain.group.service.impl;

import static gwangju.ssafy.backend.domain.group.exception.GroupError.NOT_EXISTS_GROUP;
import static gwangju.ssafy.backend.domain.group.exception.GroupError.NOT_EXISTS_SCHOOL;
import static gwangju.ssafy.backend.domain.group.exception.GroupError.NOT_GROUP_MANAGER;
import static gwangju.ssafy.backend.domain.group.exception.GroupError.NOT_GROUP_MEMBER;

import gwangju.ssafy.backend.domain.group.dto.CreateGroupRequest;
import gwangju.ssafy.backend.domain.group.dto.EditGroupInfoRequest;
import gwangju.ssafy.backend.domain.group.dto.GetGroupInfoDetailRequest;
import gwangju.ssafy.backend.domain.group.dto.GroupDetailInfo;
import gwangju.ssafy.backend.domain.group.dto.GroupSearchCond;
import gwangju.ssafy.backend.domain.group.dto.GroupSimpleInfo;
import gwangju.ssafy.backend.domain.group.dto.MyGroupSearchCond;
import gwangju.ssafy.backend.domain.group.entity.Group;
import gwangju.ssafy.backend.domain.group.entity.GroupMember;
import gwangju.ssafy.backend.domain.group.entity.School;
import gwangju.ssafy.backend.domain.group.entity.enums.GroupMemberRole;
import gwangju.ssafy.backend.domain.group.exception.GroupException;
import gwangju.ssafy.backend.domain.group.repository.GroupMemberRepository;
import gwangju.ssafy.backend.domain.group.repository.GroupRepository;
import gwangju.ssafy.backend.domain.group.repository.SchoolRepository;
import gwangju.ssafy.backend.domain.group.service.GroupMemberService;
import gwangju.ssafy.backend.domain.group.service.GroupService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@RequiredArgsConstructor
@Transactional
@Service
class GroupServiceImpl implements GroupService {

	private final GroupRepository groupRepository;
	private final SchoolRepository schoolRepository;
	private final GroupMemberRepository groupMemberRepository;
	private final GroupMemberService groupMemberService;

	@Override
	public void createGroup(CreateGroupRequest request) {

		School school = schoolRepository.findById(request.getSchoolId())
			.orElseThrow(() -> new GroupException(NOT_EXISTS_SCHOOL));

		Group group = Group.builder()
			.school(school)
			.name(request.getName())
			.category(request.getCategory())
			.isActive(true)
			.build();

		groupRepository.save(group);

		groupMemberService.addMemberInGroup(group.getId(), request.getUserId(),
			GroupMemberRole.MANAGER);

	}

	@Override
	public void inactiveGroup(Long groupId) {
		Group group = groupRepository.findById(groupId)
			.orElseThrow(() -> new GroupException(NOT_EXISTS_GROUP));

		group.inactivate();
	}

	@Override
	public void editGroupInfo(EditGroupInfoRequest request) {
		Group group = groupRepository.findByIdAndIsActiveIsTrue(request.getGroupId())
			.orElseThrow(() -> new GroupException(NOT_EXISTS_GROUP));

		GroupMember member = groupMemberRepository.findByGroupIdAndUserId(request.getGroupId(),
			request.getUserId()).orElseThrow(() -> new GroupException(NOT_GROUP_MEMBER));

		if (member.getRole() != GroupMemberRole.MANAGER) {
			throw new GroupException(NOT_GROUP_MANAGER);
		}

		group.editInfo(request.getName(), request.getAboutUs(),
			request.getIntroduce(), request.getThumbnail());

	}

	@Transactional(readOnly = true)
	@Override
	public List<GroupSimpleInfo> searchGroup(GroupSearchCond cond) {
		return groupRepository.findAllBySearchCond(cond);
	}

	@Transactional(readOnly = true)
	@Override
	public List<GroupSimpleInfo> searchMyGroup(MyGroupSearchCond cond) {
		return groupRepository.findMyGroups(cond);
	}

	@Override
	public GroupDetailInfo getGroupDetail(GetGroupInfoDetailRequest request) {
		// 그룹 존재 여부 확인
		Group group = groupRepository.findByIdAndIsActiveIsTrue(request.getGroupId())
			.orElseThrow(() -> new GroupException(NOT_EXISTS_GROUP));

		// 그룹원 수 카운트
		Long cnt = groupMemberRepository.countByGroup_Id(request.getGroupId());

		return GroupDetailInfo.builder()
			.groupId(request.getGroupId())
			.name(group.getName())
			.school(group.getSchool().getName())
			.category(group.getCategory().getValue())
			.aboutUs(group.getAboutUs())
			.thumb(group.getThumbnail())
			.introduce(group.getIntroduce())
			.memberCnt(cnt)
			.build();
	}

}
