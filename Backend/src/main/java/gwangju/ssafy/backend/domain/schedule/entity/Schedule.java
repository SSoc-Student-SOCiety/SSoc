package gwangju.ssafy.backend.domain.schedule.entity;

import gwangju.ssafy.backend.domain.group.entity.Group;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "GROUP_SCHEDULE")
@Entity
public class Schedule {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn
	private Group group;

	@Column
	private String title;

	@Column
	private String content;

	@Column
	private String category;

	@Column
	private LocalDate startedAt;

	public void edit(String title, String content, String category, LocalDate startedAt) {
		this.title = title;
		this.content = content;
		this.category = category;
		this.startedAt = startedAt;
	}

}
