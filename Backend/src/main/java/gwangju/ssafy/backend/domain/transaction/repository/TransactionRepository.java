package gwangju.ssafy.backend.domain.transaction.repository;

import gwangju.ssafy.backend.domain.transaction.entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionRepository extends JpaRepository<Transaction, Long>,TransactionBatchRepository, QueryDslTransactionRepository {

	int countByGroupAccount_Id(Long groupAccountId);

}
