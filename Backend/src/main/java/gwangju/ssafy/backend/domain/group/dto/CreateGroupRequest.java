package gwangju.ssafy.backend.domain.group.dto;


import gwangju.ssafy.backend.domain.group.entity.enums.GroupCategory;
import jakarta.validation.constraints.NotNull;
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
	@NotNull
	private GroupCategory category;
	private String name;
	
}
