package gwangju.ssafy.backend.domain.group.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class GetGroupSignupInfo {
    private Long groupSignUpId;
    private Long userId;
}
