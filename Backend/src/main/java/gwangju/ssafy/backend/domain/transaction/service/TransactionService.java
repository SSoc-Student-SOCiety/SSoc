package gwangju.ssafy.backend.domain.transaction.service;

import gwangju.ssafy.backend.domain.transaction.dto.EditTransactionNoteRequest;
import gwangju.ssafy.backend.domain.transaction.dto.GetTransactionsRequest;
import gwangju.ssafy.backend.domain.transaction.dto.TransactionInfo;
import java.util.List;

public interface TransactionService {

	void editNote(EditTransactionNoteRequest request);

	List<TransactionInfo> getTransactions(GetTransactionsRequest request);
}
