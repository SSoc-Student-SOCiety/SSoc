package gwangju.ssafy.backend.domain.board.dto;

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
public class EditPostRequest {

	private Long userId;
	private Long postId;

	private String title;
	private String content;

}
