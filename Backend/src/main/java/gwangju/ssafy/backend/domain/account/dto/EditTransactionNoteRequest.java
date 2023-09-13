package gwangju.ssafy.backend.domain.account.dto;

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
public class EditTransactionNoteRequest {

	private Long userId;
	private Long transactionId;
	private Long groupAccountId;
	private String note;

}
