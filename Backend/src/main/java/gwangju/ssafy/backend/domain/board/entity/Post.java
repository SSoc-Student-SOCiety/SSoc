package gwangju.ssafy.backend.domain.board.entity;

import gwangju.ssafy.backend.domain.board.entity.enums.PostCategory;
import gwangju.ssafy.backend.domain.group.entity.Group;
import gwangju.ssafy.backend.domain.user.entity.User;
import gwangju.ssafy.backend.global.common.entity.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "POST")
@Entity
public class Post extends BaseEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn
	private User user;

	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn
	private Group group;

	@Enumerated(EnumType.STRING)
	private PostCategory category;

	@Column
	private String title;

	@Lob
	private String content;

	@Column(name = "IS_ANONYMOUS")
	private boolean isAnonymous;

	public void editAll(String title, String content, PostCategory category, boolean isAnonymous) {
		this.title = title;
		this.content = content;
		this.category = category;
		this.isAnonymous = isAnonymous;
	}
}
