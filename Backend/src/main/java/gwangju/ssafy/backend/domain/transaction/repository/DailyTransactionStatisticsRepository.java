package gwangju.ssafy.backend.domain.transaction.repository;

import gwangju.ssafy.backend.domain.transaction.entity.DailyTransactionStatistics;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DailyTransactionStatisticsRepository extends JpaRepository<DailyTransactionStatistics,Long> {

}
