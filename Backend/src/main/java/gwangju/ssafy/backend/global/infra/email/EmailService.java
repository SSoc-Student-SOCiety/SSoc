package gwangju.ssafy.backend.global.infra.email;

import gwangju.ssafy.backend.global.common.dto.MailCodeDto;

public interface EmailService {
    MailCodeDto sendSimpleMessage(String to) throws Exception;
}
