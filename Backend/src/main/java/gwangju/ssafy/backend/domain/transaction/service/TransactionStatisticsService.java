package gwangju.ssafy.backend.domain.transaction.service;

import gwangju.ssafy.backend.domain.transaction.dto.DailyStatisticsInfo;
import gwangju.ssafy.backend.domain.transaction.dto.SearchDailyStatisticsRequest;
import java.util.List;

public interface TransactionStatisticsService {

	List<DailyStatisticsInfo> searchDailyStatistics(SearchDailyStatisticsRequest request);

}
