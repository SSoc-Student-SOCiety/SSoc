package gwangju.ssafy.backend.domain.group.entity.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum GroupCategory {
	COUNCIL, CLUB;

	@JsonCreator
	public static GroupCategory from(String value) {
		for (GroupCategory groupCategory : GroupCategory.values()) {
			if (groupCategory.name().equals(value)) {
				return groupCategory;
			}
		}
		return null;
	}

	@JsonValue
	public String getValue() {
		return this.name();
	}
}
