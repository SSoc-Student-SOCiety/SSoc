package gwangju.ssafy.backend.domain.user.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class MailDto {
    private String address; // 이메일 보낼 주소
    private String title;   // 이메일 제목
    private String content; // 이메일 내용
}
