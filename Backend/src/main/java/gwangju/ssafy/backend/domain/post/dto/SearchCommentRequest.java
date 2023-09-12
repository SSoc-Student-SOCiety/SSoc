package gwangju.ssafy.backend.domain.post.dto;

import gwangju.ssafy.backend.domain.post.dto.cond.SearchCommentCond;
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
public class SearchCommentRequest {

	private Long userId;
	private Long postId;

	private SearchCommentCond filter;

}
