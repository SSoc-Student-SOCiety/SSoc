package gwangju.ssafy.backend.domain.calendar.service.impl;

import gwangju.ssafy.backend.domain.calendar.dto.CreateScheduleRequest;
import gwangju.ssafy.backend.domain.calendar.dto.EditScheduleRequest;
import gwangju.ssafy.backend.domain.calendar.entity.Schedule;
import gwangju.ssafy.backend.domain.calendar.repository.ScheduleRepository;
import gwangju.ssafy.backend.domain.calendar.service.ScheduleService;
import gwangju.ssafy.backend.domain.group.entity.GroupMember;
import gwangju.ssafy.backend.domain.group.entity.enums.GroupMemberRole;
import gwangju.ssafy.backend.domain.group.repository.GroupMemberRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Transactional
@Service
public class ScheduleServiceImpl implements ScheduleService {

	private final ScheduleRepository scheduleRepository;
	private final GroupMemberRepository groupMemberRepository;

	@Override
	public Long createSchedule(CreateScheduleRequest request) {
		GroupMember manager = validateManager(request.getGroupId(),
			request.getUserId());

		return scheduleRepository.save(request.toEntity(manager.getGroup())).getId();
	}

	@Override
	public Long editSchedule(EditScheduleRequest request) {
		Schedule schedule = scheduleRepository.findById(request.getScheduleId())
			.orElseThrow(() -> new RuntimeException("존재하지 않는 일정"));

		validateManager(schedule.getGroup().getId(), request.getUserId());

		schedule.edit(request.getTitle(), request.getContent(), request.getCategory(),
			request.getStartedAt());

		return schedule.getId();
	}

	private GroupMember validateManager(Long groupId, Long userId) {
		GroupMember member = groupMemberRepository.findByGroupIdAndUserId(groupId,
				userId)
			.orElseThrow(() -> new RuntimeException("그룹원 X"));

		if (member.getRole() != GroupMemberRole.MANAGER) {
			throw new RuntimeException("그룹 매니저 X");
		}

		return member;
	}

}
