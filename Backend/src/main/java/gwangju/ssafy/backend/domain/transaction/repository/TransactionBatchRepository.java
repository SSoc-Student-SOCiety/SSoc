package gwangju.ssafy.backend.domain.transaction.repository;

import gwangju.ssafy.backend.domain.transaction.entity.Transaction;
import java.util.List;

public interface TransactionBatchRepository {

	void batchTransactions(final List<Transaction> chunk);

}
