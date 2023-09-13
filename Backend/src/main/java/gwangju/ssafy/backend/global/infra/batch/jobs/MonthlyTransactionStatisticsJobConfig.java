package gwangju.ssafy.backend.global.infra.batch.jobs;

import static gwangju.ssafy.backend.domain.account.entity.QDailyTransactionStatistics.dailyTransactionStatistics;

import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.Expressions;
import gwangju.ssafy.backend.global.infra.batch.dto.MonthlyTransactionStatisticsBatchDto;
import gwangju.ssafy.backend.global.infra.batch.library.querydsl.reader.QuerydslNoOffsetIdPagingItemReader;
import gwangju.ssafy.backend.global.infra.batch.library.querydsl.reader.expression.Expression;
import gwangju.ssafy.backend.global.infra.batch.library.querydsl.reader.options.QuerydslNoOffsetNumberOptions;
import gwangju.ssafy.backend.global.infra.batch.params.MonthlyTransactionStatisticsParam;
import jakarta.persistence.EntityManagerFactory;
import java.time.LocalDate;
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
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.database.builder.JdbcBatchItemWriterBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

@Slf4j
@Configuration
@RequiredArgsConstructor
public class MonthlyTransactionStatisticsJobConfig {

	private static final int CHUNK_SIZE = 100;
	private final EntityManagerFactory emf;
	private final MonthlyTransactionStatisticsParam jobParam;
	private final DataSource dataSource;


	@Bean
	public Job monthlyTransactionStatisticsJob(JobRepository jobRepository,
		PlatformTransactionManager platformTransactionManager) {
		return new JobBuilder("monthly_transaction_statistics", jobRepository)
			.start(monthlyTransactionStatisticsStep(jobRepository, platformTransactionManager))
			.build();
	}

	@Bean
	@JobScope
	public Step monthlyTransactionStatisticsStep(JobRepository jobRepository,
		PlatformTransactionManager transactionManager) {
		return new StepBuilder("monthly_transaction_statistics", jobRepository)
			.<MonthlyTransactionStatisticsBatchDto, MonthlyTransactionStatisticsBatchDto>chunk(
				CHUNK_SIZE, transactionManager)
			.reader(monthlyTransactionStatisticsReader())
			.writer(monthlyTransactionStatisticsWriter())
			.build();
	}

	@Bean
	@JobScope
	public QuerydslNoOffsetIdPagingItemReader<MonthlyTransactionStatisticsBatchDto, Long> monthlyTransactionStatisticsReader() {
		QuerydslNoOffsetNumberOptions<MonthlyTransactionStatisticsBatchDto, Long> options =
			new QuerydslNoOffsetNumberOptions<>(dailyTransactionStatistics.groupAccountId,
				Expression.ASC);

		LocalDate start = LocalDate.of(jobParam.getYear(), jobParam.getMonth(), 1);
		LocalDate end = LocalDate.of(jobParam.getYear(), jobParam.getMonth(), 1).plusMonths(1);

		return new QuerydslNoOffsetIdPagingItemReader<>(emf, CHUNK_SIZE, options,
			queryFactory -> queryFactory
				.select(Projections.fields(MonthlyTransactionStatisticsBatchDto.class,
					dailyTransactionStatistics.groupAccountId.as("groupAccountId"),
					dailyTransactionStatistics.withdrawal.sum().as("totalWithdrawal"),
					dailyTransactionStatistics.deposit.sum().as("totalDeposit"),
					Expressions.asDate(jobParam.getYear()).as("year"),
					Expressions.asDate(jobParam.getMonth()).as("month")))
				.from(dailyTransactionStatistics)
				.where(dailyTransactionStatistics.date.goe(start),
					dailyTransactionStatistics.date.lt(end))
				.groupBy(dailyTransactionStatistics.groupAccountId)
		);
	}

	@Bean
	@StepScope
	public JdbcBatchItemWriter<MonthlyTransactionStatisticsBatchDto> monthlyTransactionStatisticsWriter() {
		return new JdbcBatchItemWriterBuilder<MonthlyTransactionStatisticsBatchDto>()
			.dataSource(dataSource)
			.sql(
				"insert into monthly_transaction_statistics(group_account_id, withdrawal, deposit, years, months) values(:groupAccountId, :totalWithdrawal, :totalDeposit, :year, :month)")
			.beanMapped()
			.build();
	}

}
