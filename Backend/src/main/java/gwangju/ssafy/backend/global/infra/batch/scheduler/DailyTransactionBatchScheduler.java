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
public class DailyTransactionBatchScheduler {

	private final JobLauncher jobLauncher;
	private final Job dailyTransactionJob;

	@Scheduled(cron = "0 0 2 * * *")
	public void runDailyTransaction()
		throws Exception {

		log.info("======= 하루 결산 배치 시작 ========");
		LocalDate now = LocalDate.now();
		jobLauncher.run(dailyTransactionJob, new JobParametersBuilder()
			.addString("executeDate", now.toString())
			.toJobParameters());
	}


}
