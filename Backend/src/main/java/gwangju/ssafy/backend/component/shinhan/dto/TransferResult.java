package gwangju.ssafy.backend.component.shinhan.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
public class TransferResult {

	private String withdrawalAccountNumber;
	private String depositBankCode;
	private String depositAccountNumber;
	private Long amount;
	private String depositMessage;
	private String withdrawalMessage;
	private String balance;

}
