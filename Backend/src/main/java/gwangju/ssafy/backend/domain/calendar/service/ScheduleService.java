package gwangju.ssafy.backend.domain.calendar.service;

import gwangju.ssafy.backend.domain.calendar.dto.CreateScheduleRequest;

public interface ScheduleService {

	Long createSchedule(CreateScheduleRequest request);

}
