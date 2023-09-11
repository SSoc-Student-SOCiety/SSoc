package gwangju.ssafy.backend.domain.schedule.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import gwangju.ssafy.backend.domain.schedule.entity.Schedule;
import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ScheduleInfo {

	private Long scheduleId;
	private String title;
	private String content;
	private String category;
	@JsonSerialize(using = LocalDateSerializer.class)
	@JsonFormat(shape = Shape.STRING, pattern = "yyyy-MM-dd")
	private LocalDate date;

	public static ScheduleInfo of(Schedule entity) {
		return ScheduleInfo.builder()
			.scheduleId(entity.getId())
			.title(entity.getTitle())
			.content(entity.getContent())
			.category(entity.getCategory())
			.date(entity.getStartedAt())
			.build();
	}
}
