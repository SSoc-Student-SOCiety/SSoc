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
public class GetGroupInfoDetailRequest {

	private Long groupId;

}
