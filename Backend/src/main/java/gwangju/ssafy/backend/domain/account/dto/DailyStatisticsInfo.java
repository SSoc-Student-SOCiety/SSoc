package gwangju.ssafy.backend.domain.account.dto;

import gwangju.ssafy.backend.domain.account.entity.DailyTransactionStatistics;
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
public class DailyStatisticsInfo {

	private Long groupAccountId;
	private Long deposit;
	private Long withdrawal;
	private String date;
	private String dayOfWeek;

	public static DailyStatisticsInfo of(DailyTransactionStatistics entity) {
		return DailyStatisticsInfo.builder()
			.groupAccountId(entity.getGroupAccountId())
			.deposit(entity.getDeposit())
			.withdrawal(entity.getWithdrawal())
			.date(entity.getDate().toString())
			.dayOfWeek(entity.getDayOfWeek())
			.build();
	}

}
