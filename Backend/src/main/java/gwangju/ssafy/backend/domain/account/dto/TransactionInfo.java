package gwangju.ssafy.backend.domain.account.dto;

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
	private LocalDateTime date;
	private String briefs;
	private Long withdrawal;
	private Long deposit;
	private String detail;
	private Long balance;
	private String category;
	private String branch;
	private String note;

}
