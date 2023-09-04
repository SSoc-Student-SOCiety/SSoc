package gwangju.ssafy.backend.group.entity;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
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
@Table(name = "Groups")
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

	@Column
	private String category;

	@Column
	private String aboutUs;

	@Lob
	private String introduce;

	@Column
	private boolean isActive;

	public void inactivate() {
		this.isActive = false;
	}

	public void editInfo(String name, String category, String aboutUs, String introduce) {
		this.name = name;
		this.category = category;
		this.aboutUs = aboutUs;
		this.introduce = introduce;
	}
}
