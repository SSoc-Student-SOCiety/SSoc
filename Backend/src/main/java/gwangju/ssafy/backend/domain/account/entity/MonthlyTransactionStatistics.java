package gwangju.ssafy.backend.domain.account.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class MonthlyTransactionStatistics {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;


	@JoinColumn
	private Long groupAccountId;

	@Column
	private Long withdrawal;

	@Column
	private Long deposit;

	@Column(name = "years")
	private Integer year;

	@Column(name = "months")
	private Integer month;

}
