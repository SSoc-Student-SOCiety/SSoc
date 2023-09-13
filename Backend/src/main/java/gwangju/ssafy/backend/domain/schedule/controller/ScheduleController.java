package gwangju.ssafy.backend.domain.schedule.controller;

import gwangju.ssafy.backend.domain.schedule.dto.CreateScheduleRequest;
import gwangju.ssafy.backend.domain.schedule.dto.DeleteScheduleRequest;
import gwangju.ssafy.backend.domain.schedule.dto.EditScheduleRequest;
import gwangju.ssafy.backend.domain.schedule.dto.ScheduleInfo;
import gwangju.ssafy.backend.domain.schedule.dto.SearchScheduleRequest;
import gwangju.ssafy.backend.domain.schedule.service.ScheduleService;
import gwangju.ssafy.backend.domain.user.dto.LoginActiveUserDto;
import gwangju.ssafy.backend.global.common.dto.Message;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RequestMapping("/groups/{groupId}/schedules")
@RestController
public class ScheduleController {

	private final ScheduleService scheduleService;

	@PreAuthorize("hasAuthority('USER') or hasAuthority('ADMIN')")
	@PostMapping
	public ResponseEntity<Message<Long>> createSchedule(
		@AuthenticationPrincipal LoginActiveUserDto login,
		@RequestBody CreateScheduleRequest request,
		@PathVariable Long groupId
	) {
		request.setUserId(login.getId());
		request.setGroupId(groupId);
		return ResponseEntity.ok().body(Message.success(scheduleService.createSchedule(request)));
	}

	@PreAuthorize("hasAuthority('USER') or hasAuthority('ADMIN')")
	@PutMapping("/{scheduleId}")
	public ResponseEntity<Message<Long>> editSchedule(
		@AuthenticationPrincipal LoginActiveUserDto login,
		@RequestBody EditScheduleRequest request,
		@PathVariable Long scheduleId
	) {
		request.setUserId(login.getId());
		request.setScheduleId(scheduleId);
		return ResponseEntity.ok().body(Message.success(scheduleService.editSchedule(request)));
	}

	@PreAuthorize("hasAuthority('USER') or hasAuthority('ADMIN')")
	@DeleteMapping("/{scheduleId}")
	public ResponseEntity<Message<Long>> deleteSchedule(
		@AuthenticationPrincipal LoginActiveUserDto login,
		@RequestBody DeleteScheduleRequest request,
		@PathVariable("scheduleId") Long scheduleId
	) {
		request.setUserId(login.getId());
		request.setScheduleId(scheduleId);
		return ResponseEntity.ok().body(Message.success(scheduleService.deleteSchedule(request)));
	}

	@PreAuthorize("hasAuthority('USER') or hasAuthority('ADMIN')")
	@GetMapping
	public ResponseEntity<Message<List<ScheduleInfo>>> searchSchedule(
		@AuthenticationPrincipal LoginActiveUserDto login,
		SearchScheduleRequest request,
		@PathVariable Long groupId

	) {
		request.setUserId(login.getId());
		request.setGroupId(groupId);
		return ResponseEntity.ok().body(Message.success(scheduleService.searchSchedule(request)));
	}

}
