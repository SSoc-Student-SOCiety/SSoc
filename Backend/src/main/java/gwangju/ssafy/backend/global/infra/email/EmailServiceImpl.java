package gwangju.ssafy.backend.global.infra.email;

import gwangju.ssafy.backend.global.common.dto.MailCodeDto;
import gwangju.ssafy.backend.global.component.alarm.AlarmService;
import gwangju.ssafy.backend.global.exception.EmailException;
import gwangju.ssafy.backend.global.exception.GlobalError;
import jakarta.mail.Message;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.Random;

@RequiredArgsConstructor
@Service
public class EmailServiceImpl implements EmailService {

	private final AlarmService alarmService;

	private String createMessage(String to, boolean signupCheck, String key) {

		StringBuffer sb = new StringBuffer();

		sb.append("아래 코드를 복사해 입력해주세요.").append("\n")
			.append("감사합니다.").append("\n").append("\n");

		if (signupCheck) {
			sb.append("회원가입 메일 인증 코드 : ");
		} else {
			sb.append("임시 비밀번호 재발급 코드 : ");
		}

		sb.append(key).append("\n");

		return sb.toString();
	}

	@Override
	public MailCodeDto sendSimpleMessage(String to, boolean signupCheck) throws Exception {

		String ePw = createKey();
		
		try {
			if (signupCheck) {
				alarmService.sendMailAlarm("이메일 인증코드 입니다.", createMessage(to, signupCheck, ePw), to,
					"alarm");
			} else {
				alarmService.sendMailAlarm("임시 비밀번호 입니다.", createMessage(to, signupCheck, ePw), to,
					"alarm");
			}
		} catch (MailException e) {
			throw new EmailException(GlobalError.NOT_SEND_EMAIL);
		}

		return MailCodeDto.builder().userEmail(to).emailCode(ePw).build();
	}

	public static String createKey() {
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
