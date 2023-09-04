package gwangju.ssafy.backend.transaction.entity;

import gwangju.ssafy.backend.group.entity.Group;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "transactions")
@Entity
public class Transaction {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn
	private Group group;

	@Column
	private LocalDateTime date;

	@Column
	private String briefs;

	@Column
	private Long withdrawal;

	@Column
	private Long deposit;

	@Column
	private String detail;

	@Column
	private Long balance;

	@Column
	private String category;

	@Column
	private String branch;

	@Column
	private String note;

}
