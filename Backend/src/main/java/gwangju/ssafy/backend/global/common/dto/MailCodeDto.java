package gwangju.ssafy.backend.global.common.dto;

import lombok.*;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MailCodeDto {
    private String userEmail;
    private String emailCode;
}
