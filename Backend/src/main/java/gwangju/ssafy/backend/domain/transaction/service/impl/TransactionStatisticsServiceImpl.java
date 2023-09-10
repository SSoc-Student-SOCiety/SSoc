package gwangju.ssafy.backend.domain.transaction.service.impl;

import gwangju.ssafy.backend.domain.group.entity.GroupMember;
import gwangju.ssafy.backend.domain.group.repository.GroupMemberRepository;
import gwangju.ssafy.backend.domain.transaction.dto.DailyStatisticsInfo;
import gwangju.ssafy.backend.domain.transaction.dto.MonthlyStatisticsInfo;
import gwangju.ssafy.backend.domain.transaction.dto.SearchDailyStatisticsRequest;
import gwangju.ssafy.backend.domain.transaction.dto.SearchMonthlyStatisticsRequest;
import gwangju.ssafy.backend.domain.transaction.entity.DailyTransactionStatistics;
import gwangju.ssafy.backend.domain.transaction.entity.GroupAccount;
import gwangju.ssafy.backend.domain.transaction.entity.MonthlyTransactionStatistics;
import gwangju.ssafy.backend.domain.transaction.repository.DailyTransactionStatisticsRepository;
import gwangju.ssafy.backend.domain.transaction.repository.GroupAccountRepository;
import gwangju.ssafy.backend.domain.transaction.repository.MonthlyTransactionStatisticsRepository;
import gwangju.ssafy.backend.domain.transaction.service.TransactionStatisticsService;
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
			.orElseThrow(() -> new RuntimeException("그룹 계좌 존재 X"));

		if (!account.isActive()) {
			throw new RuntimeException("계좌 비활성화 상태");
		}

		GroupMember member = groupMemberRepository.findByGroupIdAndUserId(
				account.getGroup().getId(), userId)
			.orElseThrow(() -> new RuntimeException("그룹원 X"));
	}


}
