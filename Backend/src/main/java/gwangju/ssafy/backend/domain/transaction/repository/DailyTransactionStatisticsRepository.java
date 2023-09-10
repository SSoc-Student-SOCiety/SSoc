package gwangju.ssafy.backend.domain.transaction.repository;

import gwangju.ssafy.backend.domain.transaction.entity.DailyTransactionStatistics;
import java.time.LocalDate;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface DailyTransactionStatisticsRepository extends
	JpaRepository<DailyTransactionStatistics, Long> {

	@Query("select s from DailyTransactionStatistics s where s.groupAccountId = :groupAccountId and s.date >= :startDate and s.date <= :endDate")
	List<DailyTransactionStatistics> findAllByDate(@Param("groupAccountId") Long groupAccountId,
		@Param("startDate") LocalDate startDate, @Param("endDate") LocalDate endDate);


}
