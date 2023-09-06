package gwangju.ssafy.backend.domain.transaction.service.impl;

import gwangju.ssafy.backend.domain.group.entity.GroupMember;
import gwangju.ssafy.backend.domain.group.entity.enums.GroupMemberRole;
import gwangju.ssafy.backend.domain.group.repository.GroupMemberRepository;
import gwangju.ssafy.backend.domain.transaction.dto.EditTransactionNoteRequest;
import gwangju.ssafy.backend.domain.transaction.entity.GroupAccount;
import gwangju.ssafy.backend.domain.transaction.entity.Transaction;
import gwangju.ssafy.backend.domain.transaction.repository.GroupAccountRepository;
import gwangju.ssafy.backend.domain.transaction.repository.TransactionRepository;
import gwangju.ssafy.backend.domain.transaction.service.TransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Transactional
@Service
public class TransactionServiceImpl implements TransactionService {

	private final TransactionRepository transactionRepository;
	private final GroupAccountRepository groupAccountRepository;
	private final GroupMemberRepository groupMemberRepository;

	@Override
	public void editNote(EditTransactionNoteRequest request) {

		Transaction transaction = transactionRepository.findById(request.getTransactionId())
			.orElseThrow(() -> new RuntimeException("없는 거래내역"));

		// 그룹 계좌 확인
		GroupAccount account = groupAccountRepository.findById(request.getGroupAccountId())
			.orElseThrow((() -> new RuntimeException("존재하지 않는 그룹계좌")));

		// 권한 확인
		GroupMember member = groupMemberRepository.findByGroupIdAndUserId(
				account.getGroup().getId(), request.getUserId())
			.orElseThrow(() -> new RuntimeException("그룹 멤버가 아님"));

		if (!GroupMemberRole.MANAGER.equals(member.getRole())) {
			throw new RuntimeException("그룹 매니저가 아님");
		}

		transaction.changeNote(request.getNote());
	}
}
