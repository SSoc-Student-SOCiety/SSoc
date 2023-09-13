package gwangju.ssafy.backend.domain.group.dto;

import gwangju.ssafy.backend.domain.group.entity.enums.GroupMemberRole;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class DeleteGroupMemberInfo {
    private Long groupMemberId;
    private Long groupId;
    private String groupName;
    private Long userId;
    private String userEmail;
    private String userName;
    private String userNickName;
    private GroupMemberRole role;

}
