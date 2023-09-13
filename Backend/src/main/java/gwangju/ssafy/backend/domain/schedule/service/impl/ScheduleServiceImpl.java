package gwangju.ssafy.backend.domain.schedule.service.impl;

import gwangju.ssafy.backend.domain.group.entity.GroupMember;
import gwangju.ssafy.backend.domain.group.entity.enums.GroupMemberRole;
import gwangju.ssafy.backend.domain.group.repository.GroupMemberRepository;
import gwangju.ssafy.backend.domain.schedule.dto.CreateScheduleRequest;
import gwangju.ssafy.backend.domain.schedule.dto.DeleteScheduleRequest;
import gwangju.ssafy.backend.domain.schedule.dto.EditScheduleRequest;
import gwangju.ssafy.backend.domain.schedule.dto.ScheduleInfo;
import gwangju.ssafy.backend.domain.schedule.dto.SearchScheduleRequest;
import gwangju.ssafy.backend.domain.schedule.entity.Schedule;
import gwangju.ssafy.backend.domain.schedule.exception.ScheduleError;
import gwangju.ssafy.backend.domain.schedule.exception.ScheduleException;
import gwangju.ssafy.backend.domain.schedule.repository.ScheduleRepository;
import gwangju.ssafy.backend.domain.schedule.service.ScheduleService;
import java.time.LocalDate;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Transactional
@Service
public class ScheduleServiceImpl implements ScheduleService {

	private final ScheduleRepository scheduleRepository;
	private final GroupMemberRepository groupMemberRepository;

	@Override
	public Long createSchedule(CreateScheduleRequest request) {
		GroupMember member = getMember(request.getGroupId(),
			request.getUserId());

		validateManager(member);

		return scheduleRepository.save(request.toEntity(member.getGroup())).getId();
	}

	@Override
	public Long editSchedule(EditScheduleRequest request) {
		Schedule schedule = getSchedule(request.getScheduleId());

		validateManager(getMember(schedule.getGroup().getId(), request.getUserId()));

		schedule.edit(request.getTitle(), request.getContent(), request.getCategory(),
			request.getStartedAt());

		return schedule.getId();
	}

	@Override
	public Long deleteSchedule(DeleteScheduleRequest request) {
		Schedule schedule = getSchedule(request.getScheduleId());

		validateManager(getMember(schedule.getGroup().getId(), request.getUserId()));

		scheduleRepository.delete(schedule);

		return schedule.getId();
	}

	@Transactional(readOnly = true)
	@Override
	public List<ScheduleInfo> searchSchedule(SearchScheduleRequest request) {
		GroupMember member = getMember(request.getGroupId(), request.getUserId());

		LocalDate base = request.getDate();
		LocalDate start = request.getDate().minusDays(15);
		LocalDate end = request.getDate().plusDays(15);

		List<Schedule> result = scheduleRepository.findAllByGroupIdAndPeriod(
			request.getGroupId(), start, end);

		return result.stream().map(ScheduleInfo::of).toList();
	}

	private Schedule getSchedule(Long scheduleId) {
		return scheduleRepository.findById(scheduleId)
			.orElseThrow(() -> new ScheduleException(ScheduleError.NOT_EXISTS_SCHEDULE));
	}

	private GroupMember getMember(Long groupId, Long userId) {
		return groupMemberRepository.findByGroupIdAndUserId(groupId, userId)
			.orElseThrow(() -> new ScheduleException(ScheduleError.NOT_GROUP_MEMBER));
	}

	private static void validateManager(GroupMember member) {
		if (member.getRole() != GroupMemberRole.MANAGER) {
			throw new ScheduleException(ScheduleError.NOT_GROUP_MANAGER);
		}
	}

}
