package gwangju.ssafy.backend.domain.schedule.service;

import gwangju.ssafy.backend.domain.schedule.dto.CreateScheduleRequest;
import gwangju.ssafy.backend.domain.schedule.dto.DeleteScheduleRequest;
import gwangju.ssafy.backend.domain.schedule.dto.EditScheduleRequest;
import gwangju.ssafy.backend.domain.schedule.dto.ScheduleInfo;
import gwangju.ssafy.backend.domain.schedule.dto.SearchScheduleRequest;
import java.util.List;

public interface ScheduleService {

	Long createSchedule(CreateScheduleRequest request);
	Long editSchedule(EditScheduleRequest request);
	Long deleteSchedule(DeleteScheduleRequest request);
	List<ScheduleInfo> searchSchedule(SearchScheduleRequest request);


}
