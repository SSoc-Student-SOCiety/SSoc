package gwangju.ssafy.backend.global.infra.feign.shinhan.dto;

import lombok.Data;

@Data
public class ShinhanResponseHeader {

	private String successCode;
	private String resultCode;
	private String resultMessage;


}
