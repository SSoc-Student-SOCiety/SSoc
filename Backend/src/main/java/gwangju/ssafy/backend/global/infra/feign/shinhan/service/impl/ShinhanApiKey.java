package gwangju.ssafy.backend.global.infra.feign.shinhan.service.impl;


import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@Getter
public class ShinhanApiKey {

	@Value("${shinhan.apiKey}")
	private String apikey;

}
