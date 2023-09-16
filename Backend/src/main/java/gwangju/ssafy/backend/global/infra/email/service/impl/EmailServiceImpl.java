package gwangju.ssafy.backend.global.infra.email.service.impl;

import gwangju.ssafy.backend.global.common.dto.MailCodeDto;
import gwangju.ssafy.backend.global.component.alarm.AlarmService;
import gwangju.ssafy.backend.global.component.jwt.repository.RefreshRepository;
import gwangju.ssafy.backend.global.exception.EmailException;
import gwangju.ssafy.backend.global.exception.GlobalError;
import gwangju.ssafy.backend.global.infra.email.repository.EmailRepository;
import gwangju.ssafy.backend.global.infra.email.service.EmailService;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.MailException;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Random;

import static gwangju.ssafy.backend.global.exception.GlobalError.EXPIRES_SIGNUP_CODE;
import static gwangju.ssafy.backend.global.exception.GlobalError.NOT_MATCH_SIGNUP_CODE;

@RequiredArgsConstructor
@Service
public class EmailServiceImpl implements EmailService {

	private final AlarmService alarmService;

	private final EmailRepository emailRepository;

	private static final int EXPIRES_MIN = 1;	// 인증코드 인증 제한시간 1분

	private String createMessage(String to, boolean signupCheck, String key) {

		StringBuffer sb = new StringBuffer();

		sb.append("아래 코드를 복사해 입력해주세요.").append("<br><br>");

		if (signupCheck) {
			sb.append("회원가입 메일 인증 코드 : ");
		} else {
			sb.append("임시 비밀번호 재발급 코드 : ");
		}

		sb.append(key);

		return sb.toString();
	}

	@Override
	public MailCodeDto sendSimpleMessage(String to, boolean signupCheck) {

		String ePw = createKey();
		
		try {
			if (signupCheck) {
				alarmService.sendMailAlarm("이메일 인증코드 입니다.", createMessage(to, signupCheck, ePw), to,
					"alarm");
				emailRepository.save(to, ePw, EXPIRES_MIN);
			} else {
				alarmService.sendMailAlarm("임시 비밀번호 입니다.", createMessage(to, signupCheck, ePw), to,
					"alarm");
			}
		} catch (MailException e) {
			throw new EmailException(GlobalError.NOT_SEND_EMAIL);
		}

		return MailCodeDto.builder().userEmail(to).emailCode(ePw).build();
	}

	// 이메일 인증코드 체크
	@Override
	public void signupCodeCheck(String userEmail, String requestSignupCode) {
		Optional<String> savedSignupCode = emailRepository.findSignupCode(userEmail);

		// 해당 userEmail에 맞는 value값(이메일 인증코드)가 존재하지 않는 경우
		if(savedSignupCode.isEmpty()) {
			throw new EmailException(EXPIRES_SIGNUP_CODE);
		}

        if(!savedSignupCode.get().equals(requestSignupCode)) {
			throw new EmailException(NOT_MATCH_SIGNUP_CODE);
        }
		else {
			emailRepository.deleteSignupCode(userEmail);
		}

    }

	private String createKey() {
		StringBuffer key = new StringBuffer();
		Random rnd = new Random();

		// 인증코드 8자리로
		for (int i = 0; i < 8; i++) {
			int index = rnd.nextInt(3); // 0 ~ 2 까지 랜덤

			switch (index) {
				case 0:
					key.append((char) ((int) (rnd.nextInt(26)) + 97));
					break;
				case 1:
					key.append((char) ((int) (rnd.nextInt(26)) + 65));
					break;
				case 2:
					key.append((rnd.nextInt(10)));
					break;
			}
		}
		return key.toString();
	}
}
