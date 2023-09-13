package gwangju.ssafy.backend.domain.account.repository;

import gwangju.ssafy.backend.domain.account.entity.Transaction;
import java.util.List;

public interface TransactionBatchRepository {

	void batchTransactions(final List<Transaction> chunk);

}
