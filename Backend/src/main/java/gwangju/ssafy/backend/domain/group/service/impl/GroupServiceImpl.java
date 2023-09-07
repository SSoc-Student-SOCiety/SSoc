package gwangju.ssafy.backend.domain.group.service.impl;

import gwangju.ssafy.backend.domain.group.dto.CreateGroupRequest;
import gwangju.ssafy.backend.domain.group.dto.EditGroupInfoRequest;
import gwangju.ssafy.backend.domain.group.entity.Group;
import gwangju.ssafy.backend.domain.group.entity.GroupMember;
import gwangju.ssafy.backend.domain.group.entity.School;
import gwangju.ssafy.backend.domain.group.entity.enums.GroupMemberRole;
import gwangju.ssafy.backend.domain.group.repository.GroupMemberRepository;
import gwangju.ssafy.backend.domain.group.repository.GroupRepository;
import gwangju.ssafy.backend.domain.group.repository.SchoolRepository;
import gwangju.ssafy.backend.domain.group.service.GroupMemberService;
import gwangju.ssafy.backend.domain.group.service.GroupService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


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

		group.editInfo(request.getName(),  request.getAboutUs(),
			request.getIntroduce(),request.getThumbnail());

	}

}
