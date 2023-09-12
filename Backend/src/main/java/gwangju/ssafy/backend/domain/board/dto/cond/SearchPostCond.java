package gwangju.ssafy.backend.domain.board.dto.cond;

import gwangju.ssafy.backend.domain.board.entity.enums.PostCategory;
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
public class SearchPostCond {

	private String keyword;
	private PostCategory category;

	private Long lastPostId;

	@NotNull
	@Min(1)
	@Max(100)
	private Long pageSize;

}
