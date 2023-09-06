package gwangju.ssafy.backend.domain.transaction.dto;

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
public class SendAuthCodeRequest {

	private Long userId;
	private Long groupId;
	private String accountNumber;
	private String bankCode;
	private String bankName;

}
