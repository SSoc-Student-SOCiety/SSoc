package gwangju.ssafy.backend.global.infra.batch.dto;

import gwangju.ssafy.backend.domain.transaction.entity.MonthlyTransactionStatistics;
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
public class MonthlyTransactionStatisticsBatchDto {
	// groupAccountId
	private Long groupAccountId;
	private Long totalWithdrawal;
	private Long totalDeposit;
	private Integer year;
	private Integer month;

	public MonthlyTransactionStatistics toEntity() {
		return MonthlyTransactionStatistics.builder()
			.groupAccountId(this.groupAccountId)
			.withdrawal(this.totalWithdrawal)
			.deposit(this.totalDeposit)
			.year(this.year)
			.month(this.month)
			.build();
	}

}
