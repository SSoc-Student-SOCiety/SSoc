package gwangju.ssafy.backend.global.component.alarm.impl;

import gwangju.ssafy.backend.global.component.alarm.AlarmService;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import java.util.HashMap;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

@RequiredArgsConstructor
@Service
public class MailAlarmServiceImpl implements AlarmService {

	private final JavaMailSender mailSender;
	private final TemplateEngine templateEngine;

	@Override
	public void sendMailAlarm(String title, String contents, String receiver,String template) {
		try {
			MimeMessage message = mailSender.createMimeMessage();
			MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");

			//메일 제목 설정
			helper.setSubject(title);

			//수신자 설정
			helper.setTo(receiver);

			//템플릿에 전달할 데이터 설정
			HashMap<String, String> emailValues = new HashMap<>();
			emailValues.put("title", title);
			emailValues.put("contents", contents);

			Context context = new Context();
			emailValues.forEach((key, value)->{
				context.setVariable(key, value);
			});

			//메일 내용 설정 : 템플릿 프로세스
			String html = templateEngine.process(template, context);
			helper.setText(html, true);

			//메일 보내기
			mailSender.send(message);
		}catch (MessagingException e) {
			throw new RuntimeException(e);
		}

	}
}
