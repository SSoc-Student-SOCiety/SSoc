package gwangju.ssafy.backend.global.infra.feign.shinhan.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

public class ShinhanTransferApi {

	@Getter
	@NoArgsConstructor
	@AllArgsConstructor
	@Builder
	public static class Request {

		private String 출금계좌번호;
		private String 입금은행코드;
		private String 입금계좌번호;
		private Long 이체금액;
		private String 입금계좌통장메모;
		private String 출금계좌통장메모;

	}

	@Getter
	@NoArgsConstructor
	@AllArgsConstructor
	public static class Response {
		private String 출금계좌번호;
		private String 입금은행코드;
		private String 입금계좌번호;
		private Long 이체금액;
		private String 입금계좌통장메모;
		private String 출금계좌통장메모;
		private String 거래후잔액;

		public TransferResult toDto(){
			return TransferResult.builder()
				.withdrawalAccountNumber(this.출금계좌번호)
				.depositBankCode(this.입금은행코드)
				.depositAccountNumber(this.입금계좌번호)
				.amount(this.이체금액)
				.depositMessage(this.입금계좌통장메모)
				.withdrawalMessage(this.출금계좌통장메모)
				.balance(this.거래후잔액)
				.build();
		}
	}
}
