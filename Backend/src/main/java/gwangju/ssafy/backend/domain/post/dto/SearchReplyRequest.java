package gwangju.ssafy.backend.domain.post.dto;

import gwangju.ssafy.backend.domain.post.dto.cond.SearchCommentCond;
import gwangju.ssafy.backend.domain.post.dto.cond.SearchReplyCond;
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
public class SearchReplyRequest {

	private Long userId;
	private Long postId;
	private Long commentId;
	private SearchReplyCond filter;

}
