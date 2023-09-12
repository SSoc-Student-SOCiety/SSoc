package gwangju.ssafy.backend.domain.group.service.impl;

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
			.orElseThrow(() -> new RuntimeException("존재하지 않는 학교"));

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
			.orElseThrow(() -> new RuntimeException("존재하지 않는 그룹"));

		group.inactivate();
	}

	@Override
	public void editGroupInfo(EditGroupInfoRequest request) {
		GroupMember member = groupMemberRepository.findByGroupIdAndUserId(request.getGroupId(),
			request.getUserId()).orElseThrow(() -> new RuntimeException("존재하지 않는 그룹원"));

		if (member.getRole() != GroupMemberRole.MANAGER) {
			throw new RuntimeException("권한이 없는 그룹원");
		}

		Group group = groupRepository.findByIdAndIsActiveIsTrue(member.getGroup().getId())
			.orElseThrow(() -> new RuntimeException("존재하지 않는 그룹"));

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
			.orElseThrow(() -> new RuntimeException("해당 그룹이 없음"));

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
