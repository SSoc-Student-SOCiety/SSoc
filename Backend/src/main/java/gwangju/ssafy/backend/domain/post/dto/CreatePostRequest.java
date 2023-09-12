package gwangju.ssafy.backend.domain.post.dto;

import gwangju.ssafy.backend.domain.post.entity.enums.PostCategory;
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
public class CreatePostRequest {

	private Long userId;
	private Long groupId;
	private String title;
	private String content;
	private PostCategory category;
	private Boolean isAnonymous;

}
