package gwangju.ssafy.backend.domain.post.dto;

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
public class EditCommentRequest {

	private Long userId;
	private Long commentId;
	private String content;
	private Boolean isAnonymous;

}
