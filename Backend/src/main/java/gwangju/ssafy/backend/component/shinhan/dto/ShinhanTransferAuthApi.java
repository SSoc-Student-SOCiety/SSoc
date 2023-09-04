package gwangju.ssafy.backend.component.shinhan.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

public class ShinhanTransferAuthApi {
	@Getter
	@NoArgsConstructor
	@AllArgsConstructor
	@Builder
	public static class Request  {
		private String 입금은행코드;
		private String 입금계좌번호;
		private String 입금통장메모;
	}

	@Getter
	@NoArgsConstructor
	@AllArgsConstructor
	public static class Response {
		private String 입금은행코드;
		private String 입금계좌번호;
	}
}
