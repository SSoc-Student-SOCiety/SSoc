package gwangju.ssafy.backend.global.infra.feign.shinhan;


import gwangju.ssafy.backend.global.infra.feign.shinhan.dto.TransactionInfo;
import gwangju.ssafy.backend.global.infra.feign.shinhan.dto.TransferRequest;
import gwangju.ssafy.backend.global.infra.feign.shinhan.dto.TransferResult;
import gwangju.ssafy.backend.global.infra.feign.shinhan.dto.DepositHolderName;
import gwangju.ssafy.backend.global.infra.feign.shinhan.dto.BalanceDetail;

public interface ShinhanBankService {


	BalanceDetail getBalanceDetail(String accountNumber);

	TransactionInfo getAccountTransaction(String accountNumber);

	DepositHolderName searchDepositHolderName(String depositBankCode, String depositAccountNumber);

	TransferResult transferMoney(TransferRequest request);

	void transferAuth(String bankCode, String accountNumber, String message);

}
