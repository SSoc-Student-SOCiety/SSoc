package gwangju.ssafy.backend.group.dto;


import gwangju.ssafy.backend.group.entity.Group;
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
public class CreateGroupRequest {

	private Long userId;
	private Long schoolId;
	private String name;
	
}
