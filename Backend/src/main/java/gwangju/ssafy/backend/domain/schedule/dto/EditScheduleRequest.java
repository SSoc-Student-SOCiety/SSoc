package gwangju.ssafy.backend.domain.schedule.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import java.time.LocalDate;
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
public class EditScheduleRequest {

	private Long userId;
	private Long scheduleId;
	private String title;
	private String content;
	private String category;
	@JsonDeserialize(using = LocalDateDeserializer.class)
	@JsonFormat(shape = Shape.STRING, pattern = "yyyy-MM-dd")
	private LocalDate startedAt;

}
