package gwangju.ssafy.backend.domain.transaction.dto;


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
public class SearchMonthlyStatisticsRequest {

	private Long userId;
	private Long groupAccountId;
	private Integer year;

}
