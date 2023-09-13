package gwangju.ssafy.backend.domain.account.service;

import gwangju.ssafy.backend.domain.account.dto.DailyStatisticsInfo;
import gwangju.ssafy.backend.domain.account.dto.MonthlyStatisticsInfo;
import gwangju.ssafy.backend.domain.account.dto.SearchDailyStatisticsRequest;
import gwangju.ssafy.backend.domain.account.dto.SearchMonthlyStatisticsRequest;
import java.util.List;

public interface TransactionStatisticsService {

	List<DailyStatisticsInfo> searchDailyStatistics(SearchDailyStatisticsRequest request);

	List<MonthlyStatisticsInfo> searchMonthlyStatistics(SearchMonthlyStatisticsRequest request);
}
