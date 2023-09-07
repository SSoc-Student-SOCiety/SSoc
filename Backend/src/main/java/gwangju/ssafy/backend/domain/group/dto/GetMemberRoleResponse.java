package gwangju.ssafy.backend.domain.group.dto;

import gwangju.ssafy.backend.domain.group.entity.GroupMember;
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
public class GetMemberRoleResponse {

	private Long userId;
	private Long groupId;
	private Long groupMemberId;
	private String groupMemberRole;

	public static GetMemberRoleResponse of(GroupMember member) {
		return GetMemberRoleResponse.builder()
			.groupId(member.getGroup().getId())
			.userId(member.getUser().getId())
			.groupMemberId(member.getId())
			.groupMemberRole(member.getRole().toString())
			.build();
	}
}
