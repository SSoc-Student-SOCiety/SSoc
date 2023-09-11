package gwangju.ssafy.backend.domain.calendar.service;

import gwangju.ssafy.backend.domain.calendar.dto.CreateScheduleRequest;
import gwangju.ssafy.backend.domain.calendar.dto.DeleteScheduleRequest;
import gwangju.ssafy.backend.domain.calendar.dto.EditScheduleRequest;
import gwangju.ssafy.backend.domain.calendar.dto.ScheduleInfo;
import gwangju.ssafy.backend.domain.calendar.dto.SearchScheduleRequest;
import java.util.List;

public interface ScheduleService {

	Long createSchedule(CreateScheduleRequest request);
	Long editSchedule(EditScheduleRequest request);
	Long deleteSchedule(DeleteScheduleRequest request);
	List<ScheduleInfo> searchSchedule(SearchScheduleRequest request);


}
