package gwangju.ssafy.backend.global.infra.batch.dto;

import gwangju.ssafy.backend.domain.account.entity.DailyTransactionStatistics;
import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DailyTransactionStatisticsBatchDto {
	// groupAccountId
	private Long id;
	private Long totalWithdrawal;
	private Long totalDeposit;
	private LocalDate date;

	public DailyTransactionStatistics toEntity() {
		return DailyTransactionStatistics.builder()
			.groupAccountId(this.id)
			.withdrawal(this.totalWithdrawal)
			.deposit(this.totalDeposit)
			.date(this.date)
			.dayOfWeek(this.date.getDayOfWeek().name())
			.build();
	}
}
