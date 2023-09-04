package gwangju.ssafy.backend.global.infra.feign.shinhan.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

public class ShinhanSearchNameApi {

	@Getter
	@NoArgsConstructor
	@AllArgsConstructor
	@Builder
	public static class Request {
		private String 입금은행코드;
		private String 입금계좌번호;
	}

	@Getter
	@NoArgsConstructor
	@AllArgsConstructor
	public static class Response {
		private String 입금은행코드;
		private String 입금계좌번호;
		private String 입금계좌성명;

		public DepositHolderName toDto(){
			return DepositHolderName.builder()
				.backCode(this.입금은행코드)
				.accountNumber(this.입금계좌번호)
				.name(this.입금계좌성명)
				.build();
		}
	}
}
