package gwangju.ssafy.backend.domain.calendar.controller;

import gwangju.ssafy.backend.domain.calendar.dto.CreateScheduleRequest;
import gwangju.ssafy.backend.domain.calendar.dto.DeleteScheduleRequest;
import gwangju.ssafy.backend.domain.calendar.dto.EditScheduleRequest;
import gwangju.ssafy.backend.domain.calendar.dto.ScheduleInfo;
import gwangju.ssafy.backend.domain.calendar.dto.SearchScheduleRequest;
import gwangju.ssafy.backend.domain.calendar.service.ScheduleService;
import gwangju.ssafy.backend.global.common.dto.Message;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RequestMapping("/group/schedule")
@RestController
public class ScheduleController {

	private final ScheduleService scheduleService;

	@PostMapping
	public ResponseEntity<Message<Long>> createSchedule(@RequestBody CreateScheduleRequest request) {
		return ResponseEntity.ok().body(Message.success(scheduleService.createSchedule(request)));
	}

	@PutMapping("/{scheduleId}")
	public ResponseEntity<Message<Long>> editSchedule(@RequestBody EditScheduleRequest request,
		@PathVariable("scheduleId") Long scheduleId) {
		request.setScheduleId(scheduleId);
		return ResponseEntity.ok().body(Message.success(scheduleService.editSchedule(request)));
	}

	@DeleteMapping("/{scheduleId}")
	public ResponseEntity<Message<Long>> deleteSchedule(@RequestBody DeleteScheduleRequest request,
		@PathVariable("scheduleId") Long scheduleId) {
		request.setScheduleId(scheduleId);
		return ResponseEntity.ok().body(Message.success(scheduleService.deleteSchedule(request)));
	}

	@GetMapping
	public ResponseEntity<Message<List<ScheduleInfo>>> searchSchedule(@RequestBody SearchScheduleRequest request) {
		return ResponseEntity.ok().body(Message.success(scheduleService.searchSchedule(request)));
	}

}
