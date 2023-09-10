package gwangju.ssafy.backend.domain.transaction.controller;


import gwangju.ssafy.backend.domain.transaction.dto.DailyStatisticsInfo;
import gwangju.ssafy.backend.domain.transaction.dto.MonthlyStatisticsInfo;
import gwangju.ssafy.backend.domain.transaction.dto.SearchDailyStatisticsRequest;
import gwangju.ssafy.backend.domain.transaction.dto.SearchMonthlyStatisticsRequest;
import gwangju.ssafy.backend.domain.transaction.service.TransactionStatisticsService;
import gwangju.ssafy.backend.global.common.dto.Message;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RequestMapping("/group/account/statistics")
@RestController
public class TransactionStatisticsController {

	private final TransactionStatisticsService transactionStatisticsService;

	@PostMapping("/daily")
	public ResponseEntity<Message<List<DailyStatisticsInfo>>> editNote(@RequestBody SearchDailyStatisticsRequest request) {
		return ResponseEntity.ok()
			.body(Message.success(transactionStatisticsService.searchDailyStatistics(request)));
	}

	@PostMapping("/monthly")
	public ResponseEntity<Message<List<MonthlyStatisticsInfo>>> editNote(@RequestBody SearchMonthlyStatisticsRequest request) {
		return ResponseEntity.ok()
			.body(Message.success(transactionStatisticsService.searchMonthlyStatistics(request)));
	}

}
