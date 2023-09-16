package gwangju.ssafy.backend.domain.account.service.impl;

import static gwangju.ssafy.backend.domain.account.exception.AccountError.ALREADY_REGISTERED_ACCOUNT;
import static gwangju.ssafy.backend.domain.account.exception.AccountError.ALREADY_SENDED_AUTHCODE;
import static gwangju.ssafy.backend.domain.account.exception.AccountError.NOT_EXISTS_ACCOUNT;
import static gwangju.ssafy.backend.domain.account.exception.AccountError.NOT_EXISTS_AUTHCODE;
import static gwangju.ssafy.backend.domain.account.exception.AccountError.NOT_EXISTS_GROUP;
import static gwangju.ssafy.backend.domain.account.exception.AccountError.NOT_GROUP_MANAGER;
import static gwangju.ssafy.backend.domain.account.exception.AccountError.NOT_GROUP_MEMBER;
import static gwangju.ssafy.backend.domain.account.exception.AccountError.NOT_MATCHED_AUTHCODE;

import gwangju.ssafy.backend.domain.account.dto.BalanceInfo;
import gwangju.ssafy.backend.domain.account.dto.GetMyGroupAccountRequest;
import gwangju.ssafy.backend.domain.account.dto.GroupAccountInfo;
import gwangju.ssafy.backend.domain.account.dto.MonthlyStatisticsInfo;
import gwangju.ssafy.backend.domain.account.dto.RegisterGroupAccountRequest;
import gwangju.ssafy.backend.domain.account.dto.SendAuthCodeRequest;
import gwangju.ssafy.backend.domain.account.dto.UnregisterGroupAccountRequest;
import gwangju.ssafy.backend.domain.account.entity.GroupAccount;
import gwangju.ssafy.backend.domain.account.entity.MonthlyTransactionStatistics;
import gwangju.ssafy.backend.domain.account.exception.AccountException;
import gwangju.ssafy.backend.domain.account.repository.GroupAccountRepository;
import gwangju.ssafy.backend.domain.account.repository.MonthlyTransactionStatisticsRepository;
import gwangju.ssafy.backend.domain.account.service.GroupAccountService;
import gwangju.ssafy.backend.domain.group.entity.Group;
import gwangju.ssafy.backend.domain.group.entity.GroupMember;
import gwangju.ssafy.backend.domain.group.entity.enums.GroupMemberRole;
import gwangju.ssafy.backend.domain.group.repository.GroupMemberRepository;
import gwangju.ssafy.backend.domain.group.repository.GroupRepository;
import gwangju.ssafy.backend.global.common.entity.vo.Bank;
import gwangju.ssafy.backend.global.infra.feign.shinhan.dto.BalanceDetail;
import gwangju.ssafy.backend.global.infra.feign.shinhan.service.ShinhanBankService;
import gwangju.ssafy.backend.global.utils.AuthCodeGenerator;
import jakarta.transaction.Transactional;
import java.time.Duration;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Transactional
@Service
class GroupAccountServiceImpl implements GroupAccountService {

	private final GroupAccountRepository groupAccountRepository;
	private final ShinhanBankService shinhanBankService;
	private final GroupRepository groupRepository;
	private final GroupMemberRepository groupMemberRepository;
	private final RedisTemplate<String, String> redisTemplate;
	private final static String KEY_PREFIX = "registerGroupAccount::";
	private final MonthlyTransactionStatisticsRepository monthlyTransactionStatisticsRepository;

	@Override
	public void sendAuthCode(SendAuthCodeRequest request) {
		// 권한 확인
		validateAuthority(request.getGroupId(), request.getUserId());

		// 그룹 계좌로 등록된 계좌인지 확인
		Optional<GroupAccount> optionalGroupAccount = groupAccountRepository.findByGroupIdAndNumber(
			request.getGroupId(),
			request.getAccountNumber());

		// 요청 계좌가 이미 등록된 계좌라면
		if (optionalGroupAccount.isPresent()) {
			GroupAccount groupAccount = optionalGroupAccount.get();
			if (groupAccount.isActive()) {
				throw new AccountException(ALREADY_REGISTERED_ACCOUNT);
			}
		}

		// 인증 코드 생성
		String authCode = AuthCodeGenerator.generate();

		// Redis에 이미 인증 코드가 있는지 확인
		if (!getAuthCodeInRedis(
			KEY_PREFIX + request.getAccountNumber() + "::" + request.getGroupId()).isEmpty()) {
			throw new AccountException(ALREADY_SENDED_AUTHCODE);
		}

		// Redis에 인증 코드 저장
		redisTemplate.opsForValue()
			.set(KEY_PREFIX + request.getAccountNumber() + "::" + request.getGroupId(), authCode,
				Duration.ofMinutes(3));

		// 신한 1원 이체 API로 해당 계좌에 인증 코드 발송
		shinhanBankService.transferAuth(request.getBankCode(), request.getAccountNumber(),
			authCode);
	}

	@Override
	public void registerGroupAccount(RegisterGroupAccountRequest request) {
		validateAuthority(request.getGroupId(), request.getUserId());

		Optional<GroupAccount> optionalGroupAccount = groupAccountRepository.findByGroupIdAndNumber(
			request.getGroupId(),
			request.getAccountNumber());

		if (optionalGroupAccount.isPresent()) {
			GroupAccount groupAccount = optionalGroupAccount.get();
			if (groupAccount.isActive()) {
				throw new AccountException(ALREADY_REGISTERED_ACCOUNT);
			} else {
				groupAccount.activate();
				return;
			}
		}

		String key = KEY_PREFIX + request.getAccountNumber() + "::" + request.getGroupId();
		System.out.println("what is this " + key);
		// 인증 코드 검증
		String authCode = getAuthCodeInRedis(key)
			.orElseThrow(() -> new AccountException(NOT_EXISTS_AUTHCODE));

		if (!request.getAuthCode().equals(authCode)) {
			throw new AccountException(NOT_MATCHED_AUTHCODE);
		}

		// GroupAccount 등록
		Group group = groupRepository.findById(request.getGroupId())
			.orElseThrow(() -> new AccountException(NOT_EXISTS_GROUP));

		GroupAccount account = GroupAccount.builder()
			.group(group)
			.number(request.getAccountNumber())
			.bank(new Bank(request.getBankName(), request.getBankCode()))
			.isActive(true)
			.build();

		//
		groupAccountRepository.save(account);

		// 레디스에서 키 삭제
		redisTemplate.delete(key);
	}

	private void validateAuthority(Long groupId, Long userId) {

		GroupMember member = groupMemberRepository.findByGroupIdAndUserId(groupId, userId)
			.orElseThrow(() -> new AccountException(NOT_GROUP_MEMBER));

		if (!member.getRole().equals(GroupMemberRole.MANAGER)) {
			throw new AccountException(NOT_GROUP_MANAGER);
		}
	}

	private Optional<String> getAuthCodeInRedis(String key) {
		return Optional.ofNullable(redisTemplate.opsForValue().get(key));
	}

	@Override
	public void unregisterGroupAccount(UnregisterGroupAccountRequest request) {
		GroupAccount groupAccount = groupAccountRepository.findById(request.getAccountId())
			.orElseThrow(() -> new AccountException(NOT_EXISTS_ACCOUNT));

		validateAuthority(groupAccount.getGroup().getId(), request.getUserId());

		groupAccount.deactivate();
	}

	@Override
	public List<GroupAccountInfo> getMyGroupAccount(GetMyGroupAccountRequest request) {

		GroupMember member = groupMemberRepository.findByGroupIdAndUserId(request.getGroupId(),
				request.getUserId())
			.orElseThrow(() -> new AccountException(NOT_GROUP_MEMBER));

		return groupAccountRepository.findAllByGroupIdAndIsActiveIsTrue(request.getGroupId())
			.stream().map(GroupAccountInfo::of).toList();
	}

	@Override
	public BalanceInfo getAccountBalance(Long userId, Long accountId) {
		GroupAccount groupAccount = groupAccountRepository.findById(accountId)
			.orElseThrow(() -> new AccountException(NOT_EXISTS_ACCOUNT));
		GroupMember member = groupMemberRepository.findByGroupIdAndUserId(groupAccount.getGroup()
				.getId(), userId)
			.orElseThrow(() -> new AccountException(NOT_GROUP_MEMBER));

		LocalDate prevMonth = LocalDate.now().minusMonths(1);
		MonthlyTransactionStatistics statistic = monthlyTransactionStatisticsRepository.findByGroupAccountIdAndYearAndMonth(
			groupAccount.getId(), prevMonth.getYear(), prevMonth.getMonthValue());

		BalanceDetail balanceDetail = shinhanBankService.getBalanceDetail(groupAccount.getNumber());

		return BalanceInfo.builder()
			.balance(balanceDetail.getBalance())
			.accountNumber(balanceDetail.getAccountNumber())
			.accountId(groupAccount.getId())
			.monthlyStatisticsInfo(MonthlyStatisticsInfo.of(statistic))
			.bank(groupAccount.getBank())
			.build();
	}

}
