package gwangju.ssafy.backend.domain.account.repository.impl;

import static gwangju.ssafy.backend.domain.account.entity.QGroupAccount.groupAccount;
import static gwangju.ssafy.backend.domain.account.entity.QTransaction.transaction;

import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import gwangju.ssafy.backend.domain.account.dto.TransactionInfo;
import gwangju.ssafy.backend.domain.account.repository.QueryDslTransactionRepository;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@RequiredArgsConstructor
@Repository
public class QueryDslTransactionRepositoryImpl implements QueryDslTransactionRepository {

	private final JPAQueryFactory jpaQueryFactory;

	@Override
	public List<TransactionInfo> findAllByGroupAccountId(Long accountId, Long limit, Long pageNum,
		LocalDate start, LocalDate end) {

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
			.where(groupAccount.id.eq(accountId), filterStartDate(start), filterEndDate(end))
			.orderBy(transaction.date.desc())
			.limit(limit)
			.offset(pageNum * limit)
			.fetch();
	}

	private BooleanExpression filterStartDate(LocalDate start) {
		return start == null ? null
			: transaction.date.goe(LocalDateTime.of(start.getYear(), start.getMonth(),
				start.getDayOfMonth(), 0, 0));
	}

	private BooleanExpression filterEndDate(LocalDate end) {
		return end == null ? null
			: transaction.date.loe(LocalDateTime.of(end.getYear(), end.getMonth(),
				end.getDayOfMonth(), 0, 0).plusDays(1));
	}
}
