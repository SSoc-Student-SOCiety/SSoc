package gwangju.ssafy.backend.domain.account.dto;

import gwangju.ssafy.backend.domain.account.entity.GroupAccount;
import gwangju.ssafy.backend.global.common.entity.vo.Bank;
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
public class BalanceInfo {
	private Long accountId;
	private String accountNumber;
	private Bank bank;
	private Long balance;
	private MonthlyStatisticsInfo monthlyStatisticsInfo;

}
