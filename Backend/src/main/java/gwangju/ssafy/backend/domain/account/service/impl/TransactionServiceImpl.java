package gwangju.ssafy.backend.domain.account.service.impl;

import static gwangju.ssafy.backend.domain.account.exception.AccountError.*;

import gwangju.ssafy.backend.domain.group.entity.GroupMember;
import gwangju.ssafy.backend.domain.group.entity.enums.GroupMemberRole;
import gwangju.ssafy.backend.domain.group.repository.GroupMemberRepository;
import gwangju.ssafy.backend.domain.account.dto.EditTransactionNoteRequest;
import gwangju.ssafy.backend.domain.account.dto.SearchTransactionsRequest;
import gwangju.ssafy.backend.domain.account.dto.TransactionInfo;
import gwangju.ssafy.backend.domain.account.entity.GroupAccount;
import gwangju.ssafy.backend.domain.account.entity.Transaction;
import gwangju.ssafy.backend.domain.account.exception.AccountException;
import gwangju.ssafy.backend.domain.account.repository.GroupAccountRepository;
import gwangju.ssafy.backend.domain.account.repository.TransactionRepository;
import gwangju.ssafy.backend.domain.account.service.TransactionService;
import java.util.List;
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
	public Long editNote(EditTransactionNoteRequest request) {

		Transaction transaction = transactionRepository.findById(request.getTransactionId())
			.orElseThrow(() -> new AccountException(NOT_EXISTS_TRANSACTION));

		GroupAccount account = groupAccountRepository.findById(request.getGroupAccountId())
			.orElseThrow((() -> new AccountException(NOT_EXISTS_ACCOUNT)));

		// 권한 확인
		GroupMember member = groupMemberRepository.findByGroupIdAndUserId(
				account.getGroup().getId(), request.getUserId())
			.orElseThrow(() -> new AccountException(NOT_GROUP_MEMBER));

		if (!GroupMemberRole.MANAGER.equals(member.getRole())) {
			throw new AccountException(NOT_GROUP_MANAGER);
		}

		transaction.changeNote(request.getNote());

		return transaction.getId();
	}

	@Transactional(readOnly = true)
	@Override
	public List<TransactionInfo> getTransactions(SearchTransactionsRequest request) {

		GroupAccount account = groupAccountRepository.findById(request.getAccountId())
			.orElseThrow(() -> new AccountException(NOT_EXISTS_ACCOUNT));

		if (!account.isActive()) {
			throw new AccountException(NOT_LINKED_ACCOUNT);
		}

		groupMemberRepository.findByGroupIdAndUserId(account.getGroup().getId(),
				request.getUserId())
			.orElseThrow(() -> new AccountException(NOT_GROUP_MEMBER));

		return transactionRepository.
			findAllByGroupAccountId(request.getAccountId(), request.getFilter());
	}
}
