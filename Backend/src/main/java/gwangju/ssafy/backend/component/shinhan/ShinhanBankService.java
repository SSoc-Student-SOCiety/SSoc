package gwangju.ssafy.backend.component.shinhan;


import gwangju.ssafy.backend.component.shinhan.dto.DepositHolderName;
import gwangju.ssafy.backend.component.shinhan.dto.TransactionInfo;
import gwangju.ssafy.backend.component.shinhan.dto.BalanceDetail;
import gwangju.ssafy.backend.component.shinhan.dto.TransferRequest;
import gwangju.ssafy.backend.component.shinhan.dto.TransferResult;

public interface ShinhanBankService {


	BalanceDetail getBalanceDetail(String accountNumber);

	TransactionInfo getAccountTransaction(String accountNumber);

	DepositHolderName searchDepositHolderName(String depositBankCode, String depositAccountNumber);

	TransferResult transferMoney(TransferRequest request);

	void transferAuth(String bankCode, String accountNumber, String message);

}
