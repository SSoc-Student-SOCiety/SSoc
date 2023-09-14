package gwangju.ssafy.backend.domain.account.dto;

import gwangju.ssafy.backend.global.component.excel.ExcelColumn;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class TransactionInfo {
	private Long transactionId;
	private Long groupAccountId;
	@ExcelColumn(headerName = "날짜")
	private LocalDateTime date;
	@ExcelColumn(headerName = "적요")
	private String briefs;
	@ExcelColumn(headerName = "출금")
	private Long withdrawal;
	@ExcelColumn(headerName = "입금")
	private Long deposit;
	@ExcelColumn(headerName = "잔고")
	private Long balance;
	@ExcelColumn(headerName = "내역")
	private String detail;
	@ExcelColumn(headerName = "카테고리")
	private String category;
	@ExcelColumn(headerName = "지점")
	private String branch;
	@ExcelColumn(headerName = "비고")
	private String note;

}
