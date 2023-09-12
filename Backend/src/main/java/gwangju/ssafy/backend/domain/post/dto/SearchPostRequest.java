package gwangju.ssafy.backend.domain.post.dto;

import gwangju.ssafy.backend.domain.post.dto.cond.SearchPostCond;
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
public class SearchPostRequest {

	private Long userId;
	private Long groupId;

	private SearchPostCond filter;

}
