package gwangju.ssafy.backend.global.infra.batch.jobs;

import static gwangju.ssafy.backend.domain.transaction.entity.QGroupAccount.groupAccount;
import static gwangju.ssafy.backend.domain.transaction.entity.QTransaction.transaction;

import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.Expressions;
import gwangju.ssafy.backend.domain.transaction.entity.DailyTransactionStatistics;
import gwangju.ssafy.backend.domain.transaction.entity.QGroupAccount;
import gwangju.ssafy.backend.global.infra.batch.dto.TransactionStatisticsBatchDto;
import gwangju.ssafy.backend.global.infra.batch.library.querydsl.reader.QuerydslNoOffsetIdPagingItemReader;
import gwangju.ssafy.backend.global.infra.batch.library.querydsl.reader.expression.Expression;
import gwangju.ssafy.backend.global.infra.batch.library.querydsl.reader.options.QuerydslNoOffsetNumberOptions;
import gwangju.ssafy.backend.global.infra.batch.params.DailyTransactionStatisticsParam;
import jakarta.persistence.EntityManagerFactory;
import java.time.LocalDateTime;
import java.time.LocalTime;
import javax.sql.DataSource;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobScope;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.database.builder.JdbcBatchItemWriterBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

@Slf4j
@Configuration
@RequiredArgsConstructor
public class DailyTransactionStatisticsJobConfig {

	private static final int CHUNK_SIZE = 100;
	private final EntityManagerFactory emf;
	private final DailyTransactionStatisticsParam jobParam;
	private final DataSource dataSource;


	@Bean
	public Job dailyTransactionStatisticsJob(JobRepository jobRepository,
		PlatformTransactionManager platformTransactionManager) {
		return new JobBuilder("daily_transaction_statistics", jobRepository)
			.start(dailyTransactionStatisticsStep(jobRepository, platformTransactionManager))
			.build();
	}

	@Bean
	@JobScope
	public Step dailyTransactionStatisticsStep(JobRepository jobRepository,
		PlatformTransactionManager transactionManager) {
		return new StepBuilder("daily_transaction_statistics", jobRepository)
			.<TransactionStatisticsBatchDto, DailyTransactionStatistics>chunk(CHUNK_SIZE,
				transactionManager)
			.reader(dailyTransactionStatisticsReader())
			.processor(dailyTransactionStatisticsProcessor())
			.writer(dailyTransactionStatisticsWriter())
			.build();
	}

	@Bean
	@JobScope
	public QuerydslNoOffsetIdPagingItemReader<TransactionStatisticsBatchDto, Long> dailyTransactionStatisticsReader() {
		QuerydslNoOffsetNumberOptions<TransactionStatisticsBatchDto, Long> options =
			new QuerydslNoOffsetNumberOptions<>(transaction.groupAccount.id, Expression.ASC);

		return new QuerydslNoOffsetIdPagingItemReader<>(emf, CHUNK_SIZE, options,
			queryFactory -> queryFactory
				.select(Projections.fields(TransactionStatisticsBatchDto.class,
					transaction.groupAccount.id.as("id"),
					transaction.withdrawal.sum().as("totalWithdrawal"),
					transaction.deposit.sum().as("totalDeposit"),
					Expressions.asDate(jobParam.getStartDate()).as("startDate"),
					Expressions.asDate(jobParam.getEndDate()).as("endDate")))
				.from(transaction)
				.where(transaction.date.goe(LocalDateTime.of(jobParam.getStartDate(),
				LocalTime.MIN)),
			transaction.date.lt(LocalDateTime.of(jobParam.getEndDate(), LocalTime.MIN)))
			.groupBy(transaction.groupAccount.id)
		);
	}

	@Bean
	@StepScope
	public ItemProcessor<TransactionStatisticsBatchDto, DailyTransactionStatistics> dailyTransactionStatisticsProcessor() {
		return TransactionStatisticsBatchDto::toEntity;
	}

	@Bean
	@StepScope
	public JdbcBatchItemWriter<DailyTransactionStatistics> dailyTransactionStatisticsWriter() {
		return new JdbcBatchItemWriterBuilder<DailyTransactionStatistics>()
			.dataSource(dataSource)
			.sql("insert into daily_transaction_statistics(group_account_id, withdrawal, deposit, date, day_of_week) values(:groupAccountId, :withdrawal, :deposit, :date, :dayOfWeek)")
			.beanMapped()
			.build();
	}

}
