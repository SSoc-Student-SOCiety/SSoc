package gwangju.ssafy.backend.domain.transaction.repository;

import gwangju.ssafy.backend.domain.transaction.dto.TransactionInfo;
import java.util.List;

public interface QueryDslTransactionRepository {

	// 그룹 필터링 및 페이징 조회
	List<TransactionInfo> findAllByGroupAccountId(Long accountId, Long limit, Long pageNum);

}
