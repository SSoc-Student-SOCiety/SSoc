package gwangju.ssafy.backend.domain.transaction.service;

import gwangju.ssafy.backend.domain.transaction.dto.DailyStatisticsInfo;
import gwangju.ssafy.backend.domain.transaction.dto.MonthlyStatisticsInfo;
import gwangju.ssafy.backend.domain.transaction.dto.SearchDailyStatisticsRequest;
import gwangju.ssafy.backend.domain.transaction.dto.SearchMonthlyStatisticsRequest;
import java.util.List;

public interface TransactionStatisticsService {

	List<DailyStatisticsInfo> searchDailyStatistics(SearchDailyStatisticsRequest request);

	List<MonthlyStatisticsInfo> searchMonthlyStatistics(SearchMonthlyStatisticsRequest request);
}
