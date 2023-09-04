package gwangju.ssafy.backend.group.service.impl;

import gwangju.ssafy.backend.group.dto.CreateGroupRequest;
import gwangju.ssafy.backend.group.entity.Group;
import gwangju.ssafy.backend.group.entity.School;
import gwangju.ssafy.backend.group.entity.enums.GroupMemberRole;
import gwangju.ssafy.backend.group.repository.GroupRepository;
import gwangju.ssafy.backend.group.repository.SchoolRepository;
import gwangju.ssafy.backend.group.service.GroupMemberService;
import gwangju.ssafy.backend.group.service.GroupService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@RequiredArgsConstructor
@Transactional
@Service
public class GroupServiceImpl implements GroupService {

	private final GroupRepository groupRepository;
	private final SchoolRepository schoolRepository;
	private final GroupMemberService groupMemberService;

	@Override
	public void createGroup(CreateGroupRequest request) {

		School school = schoolRepository.findById(request.getSchoolId())
			.orElseThrow(() -> new RuntimeException("존재하지 않는 학교"));

		Group group = Group.builder()
			.school(school)
			.name(request.getName())
			.isActive(true)
			.build();

		groupRepository.save(group);

		groupMemberService.addMemberInGroup(group.getId(), request.getUserId(),
			GroupMemberRole.MANAGER);

	}

}
