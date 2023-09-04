package gwangju.ssafy.backend.group.service.impl;

import gwangju.ssafy.backend.group.entity.Group;
import gwangju.ssafy.backend.group.entity.GroupMember;
import gwangju.ssafy.backend.group.entity.enums.GroupMemberRole;
import gwangju.ssafy.backend.group.repository.GroupMemberRepository;
import gwangju.ssafy.backend.group.repository.GroupRepository;
import gwangju.ssafy.backend.group.service.GroupMemberService;
import gwangju.ssafy.backend.user.entity.User;
import gwangju.ssafy.backend.user.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@RequiredArgsConstructor
@Transactional
@Service
public class GroupMemberServiceImpl implements GroupMemberService {

	private final GroupMemberRepository groupMemberRepository;
	private final UserRepository userRepository;
	private final GroupRepository groupRepository;

	@Override
	public void addMemberInGroup(Long groupId, Long userId, GroupMemberRole role) {

		User user = userRepository.findById(userId)
			.orElseThrow(() -> new RuntimeException("존재하지 않는 회원"));
		Group group = groupRepository.findByIdAndIsActiveIsTrue(groupId)
			.orElseThrow(() -> new RuntimeException("존재하지 않는 그룹"));

		groupMemberRepository.save(GroupMember.builder()
			.group(group)
			.user(user)
			.role(role)
			.build());
	}


}
