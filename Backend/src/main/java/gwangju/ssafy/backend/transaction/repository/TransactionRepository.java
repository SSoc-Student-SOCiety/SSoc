package gwangju.ssafy.backend.transaction.repository;

import gwangju.ssafy.backend.transaction.entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionRepository extends JpaRepository<Transaction, Long>,TransactionBatchRepository {

	int countByGroup_Id(Long groupId);

}
