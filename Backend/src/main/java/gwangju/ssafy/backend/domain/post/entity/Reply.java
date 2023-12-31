package gwangju.ssafy.backend.domain.post.entity;

import gwangju.ssafy.backend.domain.user.entity.User;
import gwangju.ssafy.backend.global.common.entity.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Reply extends BaseEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn
	private Comment comment;

	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn
	private User user;

	@Column
	private String content;

	@Column
	private boolean isAnonymous;

	@Column
	private boolean isDeleted;

	public void edit(String content, boolean isAnonymous) {
		this.content = content;
		this.isAnonymous = isAnonymous;
	}

	public void delete() {
		this.isDeleted = true;
	}
}
