package gwangju.ssafy.backend.component.shinhan.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

public class ShinhanBalanceDetailApi {
	@Getter
	@NoArgsConstructor
	@AllArgsConstructor
	@Builder
	public static class Request {
		private String 출금계좌번호;
	}

	@Getter
	@NoArgsConstructor
	@AllArgsConstructor
	public static class Response {
		private String 출금계좌번호;
		private String 지불가능잔액;


		public BalanceDetail toDto() {
			return BalanceDetail.builder()
				.balance(Long.parseLong(this.지불가능잔액))
				.accountNumber(this.출금계좌번호)
				.build();
		}
	}
}
