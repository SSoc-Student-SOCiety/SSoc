package gwangju.ssafy.backend.component.shinhan;


import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@Getter
class ShinhanApiKey {

	@Value("${shinhan.apiKey}")
	private String apikey;

}