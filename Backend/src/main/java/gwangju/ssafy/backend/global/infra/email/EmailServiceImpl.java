package gwangju.ssafy.backend.global.infra.email;

import gwangju.ssafy.backend.global.common.dto.MailCodeDto;
import gwangju.ssafy.backend.global.exception.EmailException;
import gwangju.ssafy.backend.global.exception.GlobalError;
import jakarta.mail.Message;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class EmailServiceImpl implements EmailService {
    @Autowired
    JavaMailSender emailSender;

    private static final String ePw = createKey();

    private MimeMessage createMessage(String to, boolean signupCheck) throws Exception {
        System.out.println("보내는 대상: " + to);
        System.out.println("인증 번호: " + ePw);
        MimeMessage message = emailSender.createMimeMessage();

        message.addRecipients(Message.RecipientType.TO, to);    // 보내는 대상
        if(signupCheck) {
            message.setSubject("이메일 인증코드 입니다."); // 제목
        }
        else {
            message.setSubject("임시 비밀번호 입니다.");
        }

        String msgg = "";

        msgg+= "<div style='margin:20px;'>";
        if(signupCheck) {
            msgg+= "<h1> SSoC 회원가입 이메일 인증번호</h1>";
        }
        else {
            msgg+= "<h1> SSoC 임시 비밀번호 재발급</h1>";
        }
        msgg+= "<br>";
        msgg+= "<p>아래 코드를 복사해 입력해주세요.<p>";
        msgg+= "<br>";
        msgg+= "<p>감사합니다.<p>";
        msgg+= "<br>";
        msgg+= "<div align='center' style='border:1px solid black; font-family:verdana';>";
        if(signupCheck) {
            msgg+= "<h3 style='color:blue;'>회원가입 메일 인증 코드입니다.</h3>";
        }
        else {
            msgg+= "<h3 style='color:blue;'>임시 비밀번호 재발급 코드입니다.</h3>";
        }
        msgg+= "<div style='font-size:130%'>";
        msgg+= "CODE : <strong>";
        msgg+= ePw+"</strong><div><br/> ";
        msgg+= "</div>";

        message.setText(msgg, "utf-8", "html"); // 안에 들어갈 내용 세팅
        message.setFrom(new InternetAddress("donggeun3484@gmail.com", "SSoc"));
        return message;
    }

    @Override
    public MailCodeDto sendSimpleMessage(String to, boolean signupCheck) throws Exception {
        MimeMessage message = createMessage(to, signupCheck);
        try {
            emailSender.send(message);
        }
        catch(MailException e) {
            throw new EmailException(GlobalError.NOT_SEND_EMAIL);
        }

        return MailCodeDto.builder().userEmail(to).emailCode(ePw).build();
    }

    public static String createKey() {
        StringBuffer key = new StringBuffer();
        Random rnd = new Random();

        // 인증코드 8자리로
        for(int i=0; i<8; i++) {
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
