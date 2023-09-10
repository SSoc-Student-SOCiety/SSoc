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
public class MonthlyTransactionStatisticsScheduler {

	private final JobLauncher jobLauncher;
	private final Job monthlyTransactionStatisticsJob;

	@Scheduled(cron = "0 0 5 1 1/1 ?")
	public void runDailyTransactionStatistics()
		throws Exception {

		LocalDate now = LocalDate.now();
		LocalDate date = LocalDate.of(now.getYear(), now.getMonth(), 1).minusMonths(1);

		jobLauncher.run(monthlyTransactionStatisticsJob, new JobParametersBuilder()
			.addLong("year", (long)date.getYear())
			.addLong("month",(long) date.getMonthValue())
			.toJobParameters());
	}


}
