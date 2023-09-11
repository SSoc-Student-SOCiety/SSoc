package gwangju.ssafy.backend.domain.calendar.repository;

import gwangju.ssafy.backend.domain.calendar.entity.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ScheduleRepository extends JpaRepository<Schedule, Long> {

}
