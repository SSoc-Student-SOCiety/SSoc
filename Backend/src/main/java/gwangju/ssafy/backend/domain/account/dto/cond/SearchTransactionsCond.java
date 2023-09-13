package gwangju.ssafy.backend.domain.account.dto.cond;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SearchTransactionsCond {

	private Long lastTransactionId;

	@NotNull
	@Min(1)
	@Max(100)
	private Long pageSize;

	@JsonDeserialize(using = LocalDateDeserializer.class)
	@JsonFormat(shape = Shape.STRING, pattern = "yyyy-MM-dd")
	private LocalDate startDate;

	@JsonDeserialize(using = LocalDateDeserializer.class)
	@JsonFormat(shape = Shape.STRING, pattern = "yyyy-MM-dd")
	private LocalDate endDate;

}
