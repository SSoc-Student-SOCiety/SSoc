package gwangju.ssafy.backend.domain.account.repository;

import gwangju.ssafy.backend.domain.account.dto.TransactionInfo;
import gwangju.ssafy.backend.domain.account.dto.cond.SearchTransactionsCond;
import java.time.LocalDate;
import java.util.List;

public interface QueryDslTransactionRepository {

	// 그룹 필터링 및 페이징 조회
	List<TransactionInfo> findAllByGroupAccountId(Long accountId, SearchTransactionsCond cond);

}