package gwangju.ssafy.backend.global.infra.feign.shinhan.dto;


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
public class ShinhanApiDto<H, B> {

	private H dataHeader;
	private B dataBody;

}
