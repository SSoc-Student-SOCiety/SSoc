package gwangju.ssafy.backend.email;

public interface EmailService {
    String sendSimpleMessage(String to) throws Exception;
}
