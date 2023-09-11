package gwangju.ssafy.backend.domain.schedule.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import gwangju.ssafy.backend.domain.schedule.entity.Schedule;
import gwangju.ssafy.backend.domain.group.entity.Group;
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
public class CreateScheduleRequest {

	private Long userId;
	private Long groupId;
	private String title;
	private String content;
	private String category;
	@JsonDeserialize(using = LocalDateDeserializer.class)
	@JsonFormat(shape = Shape.STRING, pattern = "yyyy-MM-dd")
	private LocalDate startedAt;

	public Schedule toEntity(Group group) {
		return Schedule.builder()
			.group(group)
			.title(this.title)
			.content(this.content)
			.category(this.category)
			.startedAt(startedAt)
			.build();
	}

}
