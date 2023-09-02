package gwangju.ssafy.backend.component.shinhan.dto;

import java.time.LocalDateTime;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
public class TransactionInfo {
	private String accountNumber;
	private String productName;
	private String balance;
	private String customerName;
	private Long transactionCnt;
	private List<Transaction> transactions;


	@AllArgsConstructor
	@NoArgsConstructor
	@Builder
	@Getter
	public static class Transaction {
		private LocalDateTime date;
		private String briefs;
		private Long withdrawal;
		private Long deposit;
		private String detail;
		private Long balance;
		private String category;
		private String branch;
	}
}
