package gwangju.ssafy.backend.domain.account.controller;

import gwangju.ssafy.backend.domain.account.dto.EditTransactionNoteRequest;
import gwangju.ssafy.backend.domain.account.dto.GetTransactionsRequest;
import gwangju.ssafy.backend.domain.account.dto.TransactionInfo;
import gwangju.ssafy.backend.domain.account.service.TransactionService;
import gwangju.ssafy.backend.domain.user.dto.LoginActiveUserDto;
import gwangju.ssafy.backend.global.common.dto.Message;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RequestMapping("/accounts/{accountId}/transactions")
@RestController
public class TransactionController {

	private final TransactionService transactionService;

	@PreAuthorize("hasAuthority('USER') or hasAuthority('ADMIN')")
	@PutMapping("/{transactionId}")
	public ResponseEntity<Message<Long>> editNote(
		@AuthenticationPrincipal LoginActiveUserDto login,
		@RequestBody EditTransactionNoteRequest request,
		@PathVariable Long accountId,
		@PathVariable Long transactionId
	) {
		request.setUserId(login.getId());
		request.setGroupAccountId(accountId);
		request.setTransactionId(transactionId);
		return ResponseEntity.ok().body(Message.success(transactionService.editNote(request)));
	}

	@PreAuthorize("hasAuthority('USER') or hasAuthority('ADMIN')")
	@PostMapping
	public ResponseEntity<Message<List<TransactionInfo>>> getTransactions(
		@RequestBody GetTransactionsRequest request
	) {
		return ResponseEntity.ok().body(Message.success(transactionService.getTransactions(request)));
	}

}
