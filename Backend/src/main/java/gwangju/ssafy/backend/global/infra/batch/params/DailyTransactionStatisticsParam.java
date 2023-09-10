package gwangju.ssafy.backend.global.infra.batch.params;


import java.time.LocalDate;
import lombok.Getter;
import lombok.Setter;
import org.springframework.batch.core.configuration.annotation.JobScope;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Getter
@Setter
@JobScope
@Component
public class DailyTransactionStatisticsParam {

	@Value("#{jobParameters[startDate]}")
	private LocalDate startDate;
	@Value("#{jobParameters[endDate]}")
	private LocalDate endDate;


}