package gwangju.ssafy.backend.domain.transaction.controller;

import gwangju.ssafy.backend.domain.transaction.dto.EditTransactionNoteRequest;
import gwangju.ssafy.backend.domain.transaction.dto.GetTransactionsRequest;
import gwangju.ssafy.backend.domain.transaction.dto.TransactionInfo;
import gwangju.ssafy.backend.domain.transaction.service.TransactionService;
import gwangju.ssafy.backend.global.common.dto.Message;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RequestMapping("/group/account/transaction")
@RestController
public class TransactionController {

	private final TransactionService transactionService;

	@PostMapping("/edit")
	public ResponseEntity<Message> editNote(@RequestBody EditTransactionNoteRequest request) {
		transactionService.editNote(request);
		return ResponseEntity.ok().body(Message.success());
	}

	@PostMapping
	public ResponseEntity<Message<List<TransactionInfo>>> getTransactions(
		@RequestBody GetTransactionsRequest request) {
		return ResponseEntity.ok().body(Message.success(transactionService.getTransactions(request)));
	}

}
