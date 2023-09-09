package gwangju.ssafy.backend.global.common.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class MailSendDto {
    private String address; // 이메일 보낼 주소
    private String title;   // 이메일 제목
    private String content; // 이메일 내용
}
