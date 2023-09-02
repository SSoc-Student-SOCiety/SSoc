package gwangju.ssafy.backend.component.shinhan.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
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
