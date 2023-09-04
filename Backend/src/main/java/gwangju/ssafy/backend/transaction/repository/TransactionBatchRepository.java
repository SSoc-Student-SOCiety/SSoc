package gwangju.ssafy.backend.transaction.repository;

import gwangju.ssafy.backend.transaction.entity.Transaction;
import java.util.List;
import org.springframework.batch.item.Chunk;

public interface TransactionBatchRepository {

	void batchTransactions(final List<Transaction> chunk);

}
