package gwangju.ssafy.backend.domain.account.service;

import gwangju.ssafy.backend.domain.account.dto.EditTransactionNoteRequest;
import gwangju.ssafy.backend.domain.account.dto.SearchTransactionsRequest;
import gwangju.ssafy.backend.domain.account.dto.TransactionInfo;
import gwangju.ssafy.backend.global.component.excel.SimpleExcelFile;
import jakarta.servlet.http.HttpServletResponse;
import java.util.List;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;

public interface TransactionService {

	Long editNote(EditTransactionNoteRequest request);

	List<TransactionInfo> getTransactions(SearchTransactionsRequest request);
	SimpleExcelFile getTransactionsAsExcel(Long accountId, Long userId);
}
