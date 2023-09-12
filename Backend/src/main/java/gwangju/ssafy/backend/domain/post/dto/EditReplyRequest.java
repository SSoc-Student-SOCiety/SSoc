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
public class EditReplyRequest {

	private Long userId;
	private Long replyId;
	private String content;
	private Boolean isAnonymous;

}
