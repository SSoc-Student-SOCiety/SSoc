package gwangju.ssafy.backend.domain.transaction.repository.impl;

import static gwangju.ssafy.backend.domain.transaction.entity.QGroupAccount.groupAccount;
import static gwangju.ssafy.backend.domain.transaction.entity.QTransaction.transaction;

import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import gwangju.ssafy.backend.domain.transaction.dto.GetTransactionsRequest;
import gwangju.ssafy.backend.domain.transaction.dto.TransactionInfo;
import gwangju.ssafy.backend.domain.transaction.repository.QueryDslTransactionRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@RequiredArgsConstructor
@Repository
public class QueryDslTransactionRepositoryImpl implements QueryDslTransactionRepository {

	private final JPAQueryFactory jpaQueryFactory;

	@Override
	public List<TransactionInfo> findAllByGroupAccountId(Long accountId, Long limit, Long pageNum) {
		return jpaQueryFactory.select(Projections.fields(TransactionInfo.class,
				transaction.id.as("transactionId"),
				groupAccount.id.as("groupAccountId"),
				transaction.date.as("date"),
				transaction.briefs.as("briefs"),
				transaction.withdrawal.as("withdrawal"),
				transaction.deposit.as("deposit"),
				transaction.detail.as("detail"),
				transaction.balance.as("balance"),
				transaction.category.as("category"),
				transaction.branch.as("branch"),
				transaction.note.as("note")
				))
			.from(transaction)
			.innerJoin(transaction.groupAccount, groupAccount)
			.where(groupAccount.id.eq(accountId))
			.orderBy(transaction.date.desc())
			.limit(limit)
			.offset(pageNum * limit)
			.fetch();
	}

}
