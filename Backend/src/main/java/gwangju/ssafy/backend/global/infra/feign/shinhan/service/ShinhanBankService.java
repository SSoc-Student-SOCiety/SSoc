package gwangju.ssafy.backend.global.infra.feign.shinhan.service;


import gwangju.ssafy.backend.global.infra.feign.shinhan.dto.ShinhanTransactionInfo;
import gwangju.ssafy.backend.global.infra.feign.shinhan.dto.TransferRequest;
import gwangju.ssafy.backend.global.infra.feign.shinhan.dto.TransferResult;
import gwangju.ssafy.backend.global.infra.feign.shinhan.dto.DepositHolderName;
import gwangju.ssafy.backend.global.infra.feign.shinhan.dto.BalanceDetail;

public interface ShinhanBankService {


	BalanceDetail getBalanceDetail(String accountNumber);

	ShinhanTransactionInfo getAccountTransaction(String accountNumber);

	DepositHolderName searchDepositHolderName(String depositBankCode, String depositAccountNumber);

	TransferResult transferMoney(TransferRequest request);

	void transferAuth(String bankCode, String accountNumber, String message);

}
