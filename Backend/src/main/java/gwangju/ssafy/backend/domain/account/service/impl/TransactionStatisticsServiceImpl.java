package gwangju.ssafy.backend.domain.account.service.impl;

import gwangju.ssafy.backend.domain.account.exception.AccountError;
import gwangju.ssafy.backend.domain.account.exception.AccountException;
import gwangju.ssafy.backend.domain.group.entity.GroupMember;
import gwangju.ssafy.backend.domain.group.repository.GroupMemberRepository;
import gwangju.ssafy.backend.domain.account.dto.DailyStatisticsInfo;
import gwangju.ssafy.backend.domain.account.dto.MonthlyStatisticsInfo;
import gwangju.ssafy.backend.domain.account.dto.SearchDailyStatisticsRequest;
import gwangju.ssafy.backend.domain.account.dto.SearchMonthlyStatisticsRequest;
import gwangju.ssafy.backend.domain.account.entity.DailyTransactionStatistics;
import gwangju.ssafy.backend.domain.account.entity.GroupAccount;
import gwangju.ssafy.backend.domain.account.entity.MonthlyTransactionStatistics;
import gwangju.ssafy.backend.domain.account.repository.DailyTransactionStatisticsRepository;
import gwangju.ssafy.backend.domain.account.repository.GroupAccountRepository;
import gwangju.ssafy.backend.domain.account.repository.MonthlyTransactionStatisticsRepository;
import gwangju.ssafy.backend.domain.account.service.TransactionStatisticsService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class TransactionStatisticsServiceImpl implements TransactionStatisticsService {

	private final DailyTransactionStatisticsRepository dailyStatisticsRepository;
	private final MonthlyTransactionStatisticsRepository monthlyStatisticsRepository;
	private final GroupMemberRepository groupMemberRepository;
	private final GroupAccountRepository groupAccountRepository;


	@Override
	public List<DailyStatisticsInfo> searchDailyStatistics(SearchDailyStatisticsRequest request) {

		validateUserForAccount(request.getUserId(), request.getGroupAccountId());

		List<DailyTransactionStatistics> result = dailyStatisticsRepository.findAllByDate(
			request.getGroupAccountId(), request.getStartDate(), request.getEndDate());

		return result.stream().map(DailyStatisticsInfo::of).toList();
	}

	@Override
	public List<MonthlyStatisticsInfo> searchMonthlyStatistics(
		SearchMonthlyStatisticsRequest request) {

		validateUserForAccount(request.getUserId(), request.getGroupAccountId());

		List<MonthlyTransactionStatistics> monthly = monthlyStatisticsRepository.findAllByYear(
			request.getGroupAccountId(), request.getYear());

		return monthly.stream().map(MonthlyStatisticsInfo::of).toList();
	}

	private void validateUserForAccount(Long userId, Long groupAccountId) {
		GroupAccount account = groupAccountRepository.findById(groupAccountId)
			.orElseThrow(() -> new AccountException(AccountError.NOT_EXISTS_ACCOUNT));

		if (!account.isActive()) {
			throw new AccountException(AccountError.NOT_LINKED_ACCOUNT);
		}

		groupMemberRepository.findByGroupIdAndUserId(account.getGroup().getId(), userId)
			.orElseThrow(() -> new AccountException(AccountError.NOT_GROUP_MEMBER));
	}


}
