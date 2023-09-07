package gwangju.ssafy.backend.domain.group.dto;

import gwangju.ssafy.backend.domain.group.entity.enums.GroupCategory;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
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
public class GroupSearchCond {

	private String word;
	private GroupCategory category;


	/*  반환 size - 필수
	 * */
	@NotNull
	@Min(1)
	@Max(100)
	private Long limit;

	/*  페이지 번호 - option - default 0
	 * */
	private long pageNumber;
}
