package gwangju.ssafy.backend.global.component.alarm;

public interface AlarmService {
	void sendMailAlarm(String title, String contents, String receiver, String template);
}
