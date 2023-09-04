package gwangju.ssafy.backend.global.infra.email;

public interface EmailService {
    String sendSimpleMessage(String to) throws Exception;
}
