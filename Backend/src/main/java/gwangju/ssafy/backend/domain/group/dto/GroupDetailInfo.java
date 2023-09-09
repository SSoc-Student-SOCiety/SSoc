package gwangju.ssafy.backend.domain.group.dto;


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
public class GroupDetailInfo {

	private Long groupId;
	private String school;
	private String name;
	private String thumb;
	private String category;
	private String aboutUs;
	private String introduce;
	private Long memberCnt;

}
