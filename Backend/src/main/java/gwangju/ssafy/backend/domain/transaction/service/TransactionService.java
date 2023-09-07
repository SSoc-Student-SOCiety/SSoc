package gwangju.ssafy.backend.domain.transaction.service;

import gwangju.ssafy.backend.domain.transaction.dto.EditTransactionNoteRequest;

public interface TransactionService {

	void editNote(EditTransactionNoteRequest request);
}
