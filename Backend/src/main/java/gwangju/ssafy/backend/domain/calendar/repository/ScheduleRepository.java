package gwangju.ssafy.backend.domain.calendar.repository;

import gwangju.ssafy.backend.domain.calendar.entity.Schedule;
import java.time.LocalDate;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ScheduleRepository extends JpaRepository<Schedule, Long> {

	@Query("select s from Schedule s where s.group.id = :groupId and s.startedAt >= :startDate and s.startedAt <= :endDate")
	List<Schedule> findAllByGroupIdAndPeriod(@Param("groupId") Long groupId,
		@Param("startDate") LocalDate startDate, @Param("endDate") LocalDate endDate);
}
