package gwangju.ssafy.backend.domain.group.dto;

import gwangju.ssafy.backend.domain.group.entity.enums.GroupCategory;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class GroupSearchCond {

	private Long lastGroupId;
	private String keyword;
	private GroupCategory category;

	@NotNull
	@Min(1)
	@Max(100)
	private Long pageSize;
}
