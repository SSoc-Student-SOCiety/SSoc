package gwangju.ssafy.backend.domain.account.dto;

import gwangju.ssafy.backend.domain.account.entity.GroupAccount;
import gwangju.ssafy.backend.global.common.entity.vo.Bank;
import gwangju.ssafy.backend.global.component.excel.ExcelColumn;
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
public class GroupAccountInfo {
	private Long accountId;
	private String accountNumber;
	private Bank bank;

	public static GroupAccountInfo of(GroupAccount account) {
		return GroupAccountInfo.builder()
			.accountId(account.getId())
			.accountNumber(account.getNumber())
			.bank(account.getBank())
			.build();
	}

}
