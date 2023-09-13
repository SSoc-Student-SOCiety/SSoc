package gwangju.ssafy.backend.domain.account.dto;

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
public class RegisterGroupAccountRequest {

	private Long groupId;
	private Long userId;
	private String authCode;
	private String accountNumber;
	private String bankCode;
	private String bankName;

}
