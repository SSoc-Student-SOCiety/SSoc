package gwangju.ssafy.backend.component.batch.jobs;

import gwangju.ssafy.backend.transaction.entity.Transaction;
import gwangju.ssafy.backend.transaction.repository.TransactionBatchRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.item.Chunk;
import org.springframework.batch.item.ItemWriter;

@Slf4j
@RequiredArgsConstructor
public class DailyTransactionWriter implements
	ItemWriter<List<Transaction>> {

	private final TransactionBatchRepository transactionBatchRepository;

	@Override
	public void write(final Chunk<? extends List<Transaction>> chunk) throws Exception {
		for (List<Transaction> transactions : chunk) {
			transactionBatchRepository.batchTransactions(transactions);
		}
	}

}
