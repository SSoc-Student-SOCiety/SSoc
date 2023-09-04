package gwangju.ssafy.backend.global.config;

import feign.Logger;
import feign.Logger.Level;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@EnableFeignClients("gwangju.ssafy.backend.component")
@Configuration
public class OpenFeignConfig {


	@Bean
	Logger.Level loggerLevel() {
		return Level.FULL;
	}
}
