package gwangju.ssafy.backend.domain.account.controller;


import gwangju.ssafy.backend.domain.account.dto.DailyStatisticsInfo;
import gwangju.ssafy.backend.domain.account.dto.MonthlyStatisticsInfo;
import gwangju.ssafy.backend.domain.account.dto.SearchDailyStatisticsRequest;
import gwangju.ssafy.backend.domain.account.dto.SearchMonthlyStatisticsRequest;
import gwangju.ssafy.backend.domain.account.service.TransactionStatisticsService;
import gwangju.ssafy.backend.domain.user.dto.LoginActiveUserDto;
import gwangju.ssafy.backend.global.common.dto.Message;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RequestMapping("/accounts/{accountId}")
@RestController
public class TransactionStatisticsController {

	private final TransactionStatisticsService transactionStatisticsService;

	@PreAuthorize("hasAuthority('USER') or hasAuthority('ADMIN')")
	@GetMapping("/daily-statistics")
	public ResponseEntity<Message<List<DailyStatisticsInfo>>> searchDailyStatistics(
		@AuthenticationPrincipal LoginActiveUserDto login,
		SearchDailyStatisticsRequest request,
		@PathVariable Long accountId
	) {
		request.setUserId(login.getId());
		request.setGroupAccountId(accountId);
		return ResponseEntity.ok()
			.body(Message.success(transactionStatisticsService.searchDailyStatistics(request)));
	}

	@PreAuthorize("hasAuthority('USER') or hasAuthority('ADMIN')")
	@GetMapping("/monthly-statistics")
	public ResponseEntity<Message<List<MonthlyStatisticsInfo>>> searchMonthlyStatistics(
		@AuthenticationPrincipal LoginActiveUserDto login,
		SearchMonthlyStatisticsRequest request,
		@PathVariable Long accountId
	) {
		request.setUserId(login.getId());
		request.setGroupAccountId(accountId);
		return ResponseEntity.ok()
			.body(Message.success(transactionStatisticsService.searchMonthlyStatistics(request)));
	}

}
