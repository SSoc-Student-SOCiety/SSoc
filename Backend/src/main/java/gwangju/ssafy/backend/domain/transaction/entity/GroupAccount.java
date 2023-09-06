package gwangju.ssafy.backend.domain.transaction.entity;

import gwangju.ssafy.backend.domain.group.entity.Group;
import gwangju.ssafy.backend.global.common.entity.vo.Bank;
import jakarta.persistence.AttributeOverride;
import jakarta.persistence.AttributeOverrides;
import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(
	uniqueConstraints = {
		@UniqueConstraint(name = "unique_group_account",
			columnNames = {"number" , "group_id"})
	}
)
@Entity
public class GroupAccount {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	private Group group;

	@Column
	private String number;

	@Embedded
	@AttributeOverrides({
		@AttributeOverride(name = "name", column = @Column(name = "bank_name")),
		@AttributeOverride(name = "code", column = @Column(name = "bank_code"))
	})
	private Bank bank;

	@Column
	private boolean isActive;

	public void activate() {
		this.isActive = true;
	}

	public void deactivate() {
		this.isActive = false;
	}
}
