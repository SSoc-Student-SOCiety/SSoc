package gwangju.ssafy.backend.domain.account.repository;

import gwangju.ssafy.backend.domain.account.entity.Transaction;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;
import javax.sql.DataSource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Repository
@Transactional
class TransactionBatchRepositoryImpl implements TransactionBatchRepository {

	private final JdbcTemplate jdbcTemplate;


	public TransactionBatchRepositoryImpl(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	@Override
	public void batchTransactions(final List<Transaction> transactions) {

		log.info("===== 거래 내역 배치 JdbcTemplate 실행 =====");
		String sql =
			"INSERT INTO TRANSACTIONS (group_account_id, date, briefs, withdrawal, deposit, detail, balance, category, branch)"
				+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

			log.info("===== 저장될 거래 내역 개수 : {} =====", transactions.size());
			for (Transaction transaction : transactions) {
				jdbcTemplate.batchUpdate(sql, new BatchPreparedStatementSetter() {
					@Override
					public void setValues(PreparedStatement ps, int i) throws SQLException {
						ps.setLong(1, transaction.getGroupAccount().getId());
						ps.setTimestamp(2, Timestamp.valueOf(transaction.getDate()));
						ps.setString(3, transaction.getBriefs());
						ps.setLong(4, transaction.getWithdrawal());
						ps.setLong(5, transaction.getDeposit());
						ps.setString(6, transaction.getDetail());
						ps.setLong(7, transaction.getBalance());
						ps.setString(8, transaction.getCategory());
						ps.setString(9, transaction.getBranch());
					}

					@Override
					public int getBatchSize() {
						return 1;
					}
				});
			}
	}
}
