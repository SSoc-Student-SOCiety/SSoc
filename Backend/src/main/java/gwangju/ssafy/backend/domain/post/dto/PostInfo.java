package gwangju.ssafy.backend.domain.post.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;
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
public class PostInfo {

	private Long postId;
	private Long groupId;
	private String title;
	private String nickname;
	private LocalDate createdAt;
	private String content;
	// 추가된 부분
	private Long commentCnt;
	private String profileImg;
	private Long userId;

	public PostInfo(Long postId, Long groupId, String title, String nickname,
		LocalDateTime createdAt,
		String content, Long commentCnt, String profileImg, Long userId) {
		this.postId = postId;
		this.groupId = groupId;
		this.title = title;
		this.nickname = nickname;
		this.createdAt = LocalDate.of(createdAt.getYear(), createdAt.getMonth(),
			createdAt.getDayOfMonth());
		this.content = content;
		this.commentCnt = commentCnt;
		this.profileImg = profileImg;
		this.userId = userId;
	}
}
