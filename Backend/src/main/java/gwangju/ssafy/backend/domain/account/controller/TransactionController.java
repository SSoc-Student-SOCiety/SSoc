package gwangju.ssafy.backend.domain.account.controller;

import gwangju.ssafy.backend.domain.account.dto.EditTransactionNoteRequest;
import gwangju.ssafy.backend.domain.account.dto.SearchTransactionsRequest;
import gwangju.ssafy.backend.domain.account.dto.TransactionInfo;
import gwangju.ssafy.backend.domain.account.service.TransactionService;
import gwangju.ssafy.backend.domain.user.dto.LoginActiveUserDto;
import gwangju.ssafy.backend.global.common.dto.Message;
import gwangju.ssafy.backend.global.component.excel.SimpleExcelFile;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
	@GetMapping
	public ResponseEntity<Message<List<TransactionInfo>>> searchTransactions(
		@AuthenticationPrincipal LoginActiveUserDto login,
		SearchTransactionsRequest request,
		@PathVariable Long accountId
	) {
		request.setUserId(login.getId());
		request.setAccountId(accountId);
		return ResponseEntity.ok()
			.body(Message.success(transactionService.getTransactions(request)));
	}

	@PreAuthorize("hasAuthority('USER') or hasAuthority('ADMIN')")
	@GetMapping("/download")
	public void downloadTransactionsExcel(
		@AuthenticationPrincipal LoginActiveUserDto login,
		HttpServletResponse response,
		@PathVariable Long accountId
	) throws IOException {
		SimpleExcelFile excel =
			transactionService.getTransactionsAsExcel(accountId, login.getId());

		response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet; charset=UTF-8");
		response.setHeader("Content-Disposition",
			"attachment;filename=" + URLEncoder.encode("거래내역", "UTF-8") + ".xlsx");
		excel.writeFile(response.getOutputStream());

	}

}
