package gwangju.ssafy.backend.domain.account.dto;

import gwangju.ssafy.backend.domain.account.dto.cond.SearchTransactionsCond;
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
public class SearchTransactionsRequest {

	private Long userId;
	private Long accountId;
	private SearchTransactionsCond filter;

}
