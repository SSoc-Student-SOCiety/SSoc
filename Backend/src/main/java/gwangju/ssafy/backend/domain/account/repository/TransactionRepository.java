package gwangju.ssafy.backend.domain.account.repository;

import gwangju.ssafy.backend.domain.account.entity.Transaction;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionRepository extends JpaRepository<Transaction, Long>,TransactionBatchRepository, QueryDslTransactionRepository {

	Long countByGroupAccount_Id(Long groupAccountId);

}
