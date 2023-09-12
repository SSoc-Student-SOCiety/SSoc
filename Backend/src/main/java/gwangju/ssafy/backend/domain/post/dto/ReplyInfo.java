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
public class ReplyInfo {

	private Long replyId;
	private Long userId;
	private String nickname;
	private LocalDate createdAt;
	private String content;

	public ReplyInfo(Long replyId, Long userId, String nickname, LocalDateTime createdAt,
		String content) {
		this.replyId = replyId;
		this.userId = userId;
		this.nickname = nickname;
		this.createdAt = LocalDate.of(createdAt.getYear(), createdAt.getMonth(),
			createdAt.getDayOfMonth());
		this.content = content;
	}
}
