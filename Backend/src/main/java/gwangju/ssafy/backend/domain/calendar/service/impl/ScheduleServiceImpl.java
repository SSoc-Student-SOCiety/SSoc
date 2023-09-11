package gwangju.ssafy.backend.domain.calendar.service.impl;

import gwangju.ssafy.backend.domain.calendar.dto.CreateScheduleRequest;
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
		GroupMember member = groupMemberRepository.findByGroupIdAndUserId(request.getGroupId(),
				request.getUserId())
			.orElseThrow(() -> new RuntimeException("그룹원 X"));

		if (member.getRole() != GroupMemberRole.MANAGER) {
			throw new RuntimeException("그룹 매니저 X");
		}

		return scheduleRepository.save(request.toEntity(member.getGroup())).getId();
	}

}
