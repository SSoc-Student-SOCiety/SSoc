package gwangju.ssafy.backend.domain.transaction.service.impl;

import gwangju.ssafy.backend.domain.group.entity.GroupMember;
import gwangju.ssafy.backend.domain.group.repository.GroupMemberRepository;
import gwangju.ssafy.backend.domain.transaction.dto.DailyStatisticsInfo;
import gwangju.ssafy.backend.domain.transaction.dto.SearchDailyStatisticsRequest;
import gwangju.ssafy.backend.domain.transaction.entity.DailyTransactionStatistics;
import gwangju.ssafy.backend.domain.transaction.entity.GroupAccount;
import gwangju.ssafy.backend.domain.transaction.repository.DailyTransactionStatisticsRepository;
import gwangju.ssafy.backend.domain.transaction.repository.GroupAccountRepository;
import gwangju.ssafy.backend.domain.transaction.service.TransactionStatisticsService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class TransactionStatisticsServiceImpl implements TransactionStatisticsService {

	private final DailyTransactionStatisticsRepository dailyTransactionStatisticsRepository;
	private final GroupMemberRepository groupMemberRepository;
	private final GroupAccountRepository groupAccountRepository;


	@Override
	public List<DailyStatisticsInfo> searchDailyStatistics(SearchDailyStatisticsRequest request) {
		// 그룹 계좌 존재하는지 확인
		GroupAccount account = groupAccountRepository.findById(request.getGroupAccountId())
			.orElseThrow(() -> new RuntimeException("그룹 계좌 존재 X"));

		if (!account.isActive()) {
			throw new RuntimeException("계좌 비활성화 상태");
		}

		// 회원 권한 확인
		GroupMember member = groupMemberRepository.findByGroupIdAndUserId(
				account.getGroup().getId(), request.getUserId())
			.orElseThrow(() -> new RuntimeException("그룹원 X"));

		List<DailyTransactionStatistics> result = dailyTransactionStatisticsRepository.findAllByDate(
			request.getGroupAccountId(), request.getStartDate(), request.getEndDate());



		return result.stream().map(DailyStatisticsInfo::of).toList();
	}
}
