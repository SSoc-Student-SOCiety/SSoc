package gwangju.ssafy.backend.component.shinhan.dto;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

public class ShinhanTansactionApi {

	@Getter
	@NoArgsConstructor
	@AllArgsConstructor
	@Builder
	public static class Request {
		private String 계좌번호;
	}

	@Getter
	@NoArgsConstructor
	@AllArgsConstructor
	public static class Response {

		private String 계좌번호;
		private String 상품명;
		private String 계좌잔액;
		private String 고객명;
		private Long 거래내역반복횟수;
		private List<Transaction> 거래내역;

		public TransactionInfo toDto() {
			return TransactionInfo.builder()
				.accountNumber(this.계좌번호)
				.productName(this.상품명)
				.balance(this.계좌잔액)
				.customerName(this.고객명)
				.transactionCnt(this.거래내역반복횟수)
				.transactions(toList())
				.build();
		}

		private List<TransactionInfo.Transaction> toList() {
			ArrayList<TransactionInfo.Transaction> list = new ArrayList<>();
			for (Transaction transaction : 거래내역) {
				list.add(transaction.toDto());
			}
			return list;
		}
	}

	@Getter
	@NoArgsConstructor
	@AllArgsConstructor
	public static class Transaction {

		static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");

		private String 거래일자;
		private String 거래시간;
		private String 적요;
		private Long 출금금액;
		private Long 입금금액;
		private String 내용;
		private Long 잔액;
		private String 입지구분;
		private String 거래점명;

		public TransactionInfo.Transaction toDto() {
			return TransactionInfo.Transaction.builder()
				.date(LocalDateTime.parse(this.거래일자 + this.거래시간,formatter))
				.briefs(this.적요)
				.withdrawal(this.출금금액)
				.deposit(this.입금금액)
				.detail(this.내용)
				.balance(this.잔액)
				.category(this.입지구분.equals(1) ? "입금" : "지급")
				.branch(this.거래점명)
				.build();
		}
	}

}
