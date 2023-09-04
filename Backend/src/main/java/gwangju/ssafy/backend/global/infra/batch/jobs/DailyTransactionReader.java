package gwangju.ssafy.backend.global.infra.batch.jobs;

import gwangju.ssafy.backend.global.infra.feign.shinhan.service.ShinhanBankService;
import gwangju.ssafy.backend.global.infra.feign.shinhan.dto.TransactionInfo;
import gwangju.ssafy.backend.domain.transaction.entity.GroupAccount;
import gwangju.ssafy.backend.domain.transaction.entity.Transaction;
import gwangju.ssafy.backend.domain.transaction.repository.GroupAccountRepository;
import gwangju.ssafy.backend.domain.transaction.repository.TransactionRepository;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;


@Slf4j
@RequiredArgsConstructor
public class DailyTransactionReader implements ItemReader<List<Transaction>> {

	private final ShinhanBankService shinhanBankService;
	private final GroupAccountRepository groupAccountRepository;
	private final TransactionRepository transactionRepository;

	@Override
	public List<Transaction> read()
		throws Exception, UnexpectedInputException, ParseException, NonTransientResourceException {

		log.info("====== 모든 그룹 계좌번호 가져오기 =====");
		List<GroupAccount> all = groupAccountRepository.findAll();

		log.info("모든 그룹 계좌 수 : {}", all.size());
		ArrayList<Transaction> list = new ArrayList<>();

		for (GroupAccount groupAccount : all) {
			String accountNumber = groupAccount.getNumber();
			log.info("===== 결산 중인 계좌 : {} ======", accountNumber);
			TransactionInfo info = shinhanBankService.getAccountTransaction(
				accountNumber);

			List<TransactionInfo.Transaction> transactions = info.getTransactions();

			int cnt = transactionRepository.countByGroup_Id(groupAccount.getGroup().getId());
			log.info("===== 기존 거래내역 개수 : {} =====", cnt);

			long diff = info.getTransactionCnt() - cnt;
			log.info("===== 새로 저장할 내역 개수 : {} =====", diff);

			for (int i = 0; i < diff; i++) {
				TransactionInfo.Transaction transaction = transactions.get(i);

				Transaction result = Transaction.builder()
					.balance(transaction.getBalance())
					.deposit(transaction.getDeposit())
					.withdrawal(transaction.getWithdrawal())
					.detail(transaction.getDetail())
					.briefs(transaction.getBriefs())
					.branch(transaction.getBranch())
					.date(transaction.getDate())
					.category(transaction.getCategory())
					.group(groupAccount.getGroup())
					.build();

				list.add(result);
			}
		}

		log.info("===== 총 저장 개수 : {} =====", list.size());

		if(list.size() == 0) return null;

		return list;
	}
}
