package gwangju.ssafy.backend.global.infra.email.service;

import gwangju.ssafy.backend.global.common.dto.MailCodeDto;

public interface EmailService {
    MailCodeDto sendSimpleMessage(String to, boolean signupCheck);

    void signupCodeCheck(String userEmail, String requestSignupCode);
}
