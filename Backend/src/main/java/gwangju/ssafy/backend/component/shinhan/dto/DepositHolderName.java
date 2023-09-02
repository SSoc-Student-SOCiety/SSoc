package gwangju.ssafy.backend.component.shinhan.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
public class DepositHolderName {

	private String backCode;
	private String accountNumber;
	private String name;

}
