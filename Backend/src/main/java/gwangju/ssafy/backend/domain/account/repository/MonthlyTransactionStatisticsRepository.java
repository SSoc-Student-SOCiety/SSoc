package gwangju.ssafy.backend.domain.account.repository;

import gwangju.ssafy.backend.domain.account.entity.MonthlyTransactionStatistics;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface MonthlyTransactionStatisticsRepository extends
	JpaRepository<MonthlyTransactionStatistics, Long> {

	@Query("select s from MonthlyTransactionStatistics s "
		+ "where s.groupAccountId = :groupAccountId "
		+ "and s.year = :year")
	List<MonthlyTransactionStatistics> findAllByYear(@Param("groupAccountId") Long groupAccountId,
		@Param("year") Integer year);

}
