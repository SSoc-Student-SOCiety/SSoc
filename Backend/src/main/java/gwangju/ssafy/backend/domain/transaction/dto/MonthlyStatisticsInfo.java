package gwangju.ssafy.backend.domain.transaction.dto;

import gwangju.ssafy.backend.domain.transaction.entity.MonthlyTransactionStatistics;
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
public class MonthlyStatisticsInfo {

	private Long groupAccountId;
	private Long deposit;
	private Long withdrawal;
	private Integer year;
	private Integer month;

	public static MonthlyStatisticsInfo of(MonthlyTransactionStatistics entity) {
		return MonthlyStatisticsInfo.builder()
			.groupAccountId(entity.getGroupAccountId())
			.deposit(entity.getDeposit())
			.withdrawal(entity.getWithdrawal())
			.year(entity.getYear())
			.month(entity.getMonth())
			.build();
	}

}
