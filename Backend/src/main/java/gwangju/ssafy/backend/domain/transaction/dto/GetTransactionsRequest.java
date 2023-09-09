package gwangju.ssafy.backend.domain.transaction.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import java.awt.print.Pageable;
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
public class GetTransactionsRequest {

	private Long userId;
	private Long groupId;
	private String accountNumber;
	@NotNull
	@Min(1)
	@Max(100)
	private Long limit;
	private long pageNumber;

}
