package gwangju.ssafy.backend.global.infra.batch.jobs;

import gwangju.ssafy.backend.global.infra.feign.shinhan.service.ShinhanBankService;
import gwangju.ssafy.backend.domain.account.entity.Transaction;
import gwangju.ssafy.backend.domain.account.repository.GroupAccountRepository;
import gwangju.ssafy.backend.domain.account.repository.TransactionRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobScope;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

@Slf4j
@Configuration
@RequiredArgsConstructor
public class DailyTransactionJobConfig {

	private final ShinhanBankService shinhanBankService;
	private final GroupAccountRepository groupAccountRepository;
	private final TransactionRepository transactionRepository;

	@Bean
	public Job dailyTransactionJob(JobRepository jobRepository,
		PlatformTransactionManager platformTransactionManager) {
		return new JobBuilder("daily_transaction", jobRepository)
			.start(dailyTransactionStep(jobRepository, platformTransactionManager))
			.build();
	}

	@Bean
	@JobScope
	public Step dailyTransactionStep(JobRepository jobRepository,
		PlatformTransactionManager transactionManager) {
		return new StepBuilder("daily_transaction", jobRepository)
			.<List<Transaction>, List<Transaction>>chunk(1, transactionManager)
			.reader(transactionInfoReader())
			.writer(transactionWriter())
			.build();
	}

	@Bean
	@StepScope
	public ItemReader<List<Transaction>> transactionInfoReader() {
		return new DailyTransactionReader(shinhanBankService, groupAccountRepository,
			transactionRepository);
	}

	@Bean
	@StepScope
	public ItemWriter<List<Transaction>> transactionWriter() {
		return new DailyTransactionWriter(transactionRepository);
	}

}
