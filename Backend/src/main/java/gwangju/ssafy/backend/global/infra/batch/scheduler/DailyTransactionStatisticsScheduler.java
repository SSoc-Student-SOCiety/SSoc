package gwangju.ssafy.backend.global.infra.batch.scheduler;

import java.time.LocalDate;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Slf4j
@RequiredArgsConstructor
@Component
public class DailyTransactionStatisticsScheduler {

	private final JobLauncher jobLauncher;
	private final Job dailyTransactionStatisticsJob;

	@Scheduled(cron = "0 0 3 * * *")
	public void runDailyTransactionStatistics()
		throws Exception {

		LocalDate now = LocalDate.now();
		jobLauncher.run(dailyTransactionStatisticsJob, new JobParametersBuilder()
			.addLocalDate("startDate", now.minusDays(1))
			.addLocalDate("endDate", now)
			.toJobParameters());
	}


}
