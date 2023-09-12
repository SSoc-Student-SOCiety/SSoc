package gwangju.ssafy.backend.domain.post.entity.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum PostCategory {
	NOTICE, FREE, SUGGEST;

	@JsonCreator
	public static PostCategory from(String value) {
		for (PostCategory postCategory : PostCategory.values()) {
			if (postCategory.name().equals(value)) {
				return postCategory;
			}
		}
		throw new RuntimeException("없는 카테고리");
	}

	@JsonValue
	public String getValue() {
		return this.name();
	}
}
