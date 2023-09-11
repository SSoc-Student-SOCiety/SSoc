package gwangju.ssafy.backend.domain.calendar.service;

import gwangju.ssafy.backend.domain.calendar.dto.CreateScheduleRequest;
import gwangju.ssafy.backend.domain.calendar.dto.EditScheduleRequest;

public interface ScheduleService {

	Long createSchedule(CreateScheduleRequest request);
	Long editSchedule(EditScheduleRequest request);

}
