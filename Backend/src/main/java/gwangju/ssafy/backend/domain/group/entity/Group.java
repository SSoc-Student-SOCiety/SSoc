package gwangju.ssafy.backend.domain.group.entity;


import gwangju.ssafy.backend.domain.group.entity.enums.GroupCategory;
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
@Table(name = "`Group`")
@Entity
public class Group {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(nullable = false)
	private School school;

	@Column
	private String name;

	@Enumerated(EnumType.STRING)
	private GroupCategory category;

	@Column
	private String aboutUs;

	@Column(length = 1000)
	private String introduce;

	@Column
	private boolean isActive;

	@Column
	private String thumbnail;

	public void inactivate() {
		this.isActive = false;
	}

	public void editInfo(String name, String aboutUs, String introduce,String thumbnail) {
		this.name = name;
		this.aboutUs = aboutUs;
		this.introduce = introduce;
		this.thumbnail = thumbnail;
	}
}
