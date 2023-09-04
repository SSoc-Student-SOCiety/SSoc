package gwangju.ssafy.backend.global.infra.feign.shinhan.dto;

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
