package gwangju.ssafy.backend.domain.group.dto;

import gwangju.ssafy.backend.domain.group.entity.GroupSignup;
import gwangju.ssafy.backend.domain.user.entity.enums.UserRole;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class GroupSignupInfo {
    private Long groupSignupId;
    private Long groupId;
    private String userEmail;
    private String userName;
    private String userNickname;
    private String userImgUrl;
    private UserRole role;
    private boolean signupStatus;

    public void convert(GroupSignup groupSignup) {
        this.groupSignupId = groupSignup.getId();
        this.groupId = groupSignup.getGroup().getId();
        this.userEmail = groupSignup.getUser().getUserEmail();
        this.userName = groupSignup.getUser().getUserName();
        this.userNickname = groupSignup.getUser().getUserNickname();
        this.userImgUrl = groupSignup.getUser().getUserImageUrl();
        this.role = groupSignup.getUser().getRole();
        this.signupStatus = groupSignup.isSignupStatus();
    }
}
