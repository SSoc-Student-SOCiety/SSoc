package gwangju.ssafy.backend.domain.post.dto.cond;

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
public class SearchReplyCond {

	private Long lastReplyId;

	@NotNull
	@Min(1)
	@Max(100)
	private Long pageSize;

}
