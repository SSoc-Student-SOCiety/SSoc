package gwangju.ssafy.backend.domain.account.service;

import gwangju.ssafy.backend.domain.account.dto.EditTransactionNoteRequest;
import gwangju.ssafy.backend.domain.account.dto.SearchTransactionsRequest;
import gwangju.ssafy.backend.domain.account.dto.TransactionInfo;
import java.util.List;

public interface TransactionService {

	Long editNote(EditTransactionNoteRequest request);

	List<TransactionInfo> getTransactions(SearchTransactionsRequest request);
}
