package gwangju.ssafy.backend.domain.transaction.service.impl;

import gwangju.ssafy.backend.domain.group.entity.Group;
import gwangju.ssafy.backend.domain.group.entity.GroupMember;
import gwangju.ssafy.backend.domain.group.entity.enums.GroupMemberRole;
import gwangju.ssafy.backend.domain.group.repository.GroupMemberRepository;
import gwangju.ssafy.backend.domain.group.repository.GroupRepository;
import gwangju.ssafy.backend.domain.transaction.dto.RegisterGroupAccountRequest;
import gwangju.ssafy.backend.domain.transaction.dto.SendAuthCodeRequest;
import gwangju.ssafy.backend.domain.transaction.dto.UnregisterGroupAccountRequest;
import gwangju.ssafy.backend.domain.transaction.entity.GroupAccount;
import gwangju.ssafy.backend.domain.transaction.repository.GroupAccountRepository;
import gwangju.ssafy.backend.domain.transaction.service.GroupAccountService;
import gwangju.ssafy.backend.global.common.entity.vo.Bank;
import gwangju.ssafy.backend.global.infra.feign.shinhan.service.ShinhanBankService;
import gwangju.ssafy.backend.global.utils.AuthCodeGenerator;
import jakarta.transaction.Transactional;
import java.time.Duration;
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

	@Override
	public void sendAuthCode(SendAuthCodeRequest request) {
		// 권한 확인
		validateAuthority(request.getGroupId(), request.getUserId());

		// 그룹 계좌로 등록된 계좌인지 확인
		Optional<GroupAccount> optionalGroupAccount = groupAccountRepository.findByGroupIdAndNumber(
			request.getGroupId(),
			request.getAccountNumber());

		// 요청 계좌가 이미 등록된 계좌라면
		if(optionalGroupAccount.isPresent()){
			GroupAccount groupAccount = optionalGroupAccount.get();
			if (groupAccount.isActive()) {
				throw new RuntimeException("이미 등록된 계좌");
			}
		}

		// 인증 코드 생성
		String authCode = AuthCodeGenerator.generate();

		// Redis에 이미 인증 코드가 있는지 확인
		if (!getAuthCodeInRedis(KEY_PREFIX + request.getGroupId()).isEmpty()) {
			throw new RuntimeException("이미 인증코드가 발송되어 있음");
		}

		// Redis에 인증 코드 저장
		redisTemplate.opsForValue()
			.set(KEY_PREFIX + request.getGroupId(), authCode, Duration.ofMinutes(3));

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

		if(optionalGroupAccount.isPresent()){
			GroupAccount groupAccount = optionalGroupAccount.get();
			if (groupAccount.isActive()) {
				throw new RuntimeException("이미 등록된 계좌");
			}else {
				groupAccount.activate();
				return;
			}
		}

		String key = KEY_PREFIX + request.getGroupId();

		// 인증 코드 검증
		String authCode = getAuthCodeInRedis(key)
			.orElseThrow(() -> new RuntimeException("해당 인증 코드가 없음"));

		if (!request.getAuthCode().equals(authCode)) {
			throw new RuntimeException("인증 코드가 일치하지 않음");
		}

		// GroupAccount 등록
		Group group = groupRepository.findById(request.getGroupId())
			.orElseThrow(() -> new RuntimeException("존재하지 않는 그룹"));

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
			.orElseThrow(() -> new RuntimeException("그룹원이 아님"));

		if (!member.getRole().equals(GroupMemberRole.MANAGER)) {
			throw new RuntimeException("그룹 관리자가 아님");
		}
	}

	private Optional<String> getAuthCodeInRedis(String key) {
		return Optional.ofNullable(redisTemplate.opsForValue().get(key));
	}

	@Override
	public void unregisterGroupAccount(UnregisterGroupAccountRequest request) {
		validateAuthority(request.getGroupId(), request.getUserId());

		GroupAccount groupAccount = groupAccountRepository.findByGroupIdAndNumber(
				request.getGroupId(),
				request.getAccountNumber())
			.orElseThrow(() -> new RuntimeException("존재하지 않는 계좌"));

		groupAccount.deactivate();
	}

}
