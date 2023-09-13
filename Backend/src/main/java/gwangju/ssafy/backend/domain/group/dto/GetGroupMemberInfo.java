package gwangju.ssafy.backend.domain.group.dto;

import gwangju.ssafy.backend.domain.group.entity.Group;
import gwangju.ssafy.backend.domain.group.entity.GroupMember;
import gwangju.ssafy.backend.domain.group.entity.enums.GroupMemberRole;
import gwangju.ssafy.backend.domain.user.entity.User;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class GetGroupMemberInfo {
    private Long groupMemberId;
    private Long groupId;
    private String groupName;
    private Long userId;
    private String userEmail;
    private String userName;
    private String userNickname;
    private GroupMemberRole role;

    public void convert(GroupMember groupMember) {
        this.groupMemberId = groupMember.getId();
        this.groupId = groupMember.getGroup().getId();
        this.groupName = groupMember.getGroup().getName();
        this.userId = groupMember.getUser().getId();
        this.userEmail = groupMember.getUser().getUserEmail();
        this.userName = groupMember.getUser().getUserName();
        this.userNickname = groupMember.getUser().getUserNickname();
        this.role = groupMember.getRole();
    }
}
