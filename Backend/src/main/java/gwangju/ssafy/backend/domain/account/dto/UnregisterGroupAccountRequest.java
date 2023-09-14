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
public class UnregisterGroupAccountRequest {

	private Long userId;
	private Long accountId;

}
