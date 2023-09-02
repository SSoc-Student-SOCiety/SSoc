package gwangju.ssafy.backend.component.shinhan;

import gwangju.ssafy.backend.component.shinhan.dto.ShinhanTransactionApi;
import gwangju.ssafy.backend.component.shinhan.dto.TransactionInfo;
import gwangju.ssafy.backend.component.shinhan.dto.BalanceDetail;
import gwangju.ssafy.backend.component.shinhan.dto.ShinhanBalanceDetailApi.Request;
import gwangju.ssafy.backend.component.shinhan.dto.ShinhanBalanceDetailApi.Response;
import gwangju.ssafy.backend.component.shinhan.dto.ShinhanApiDto;
import gwangju.ssafy.backend.component.shinhan.dto.ShinhanResponseHeader;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class ShinhanBankServiceImpl implements ShinhanBankService {

	private final ShinhanApiKey apiKey;
	private final ShinhanApiFeignClient apiClient;

	@Override
	public BalanceDetail getBalanceDetail(String accountNumber) {

		ShinhanApiDto<ShinhanApiKey, Request> request = ShinhanApiDto.<ShinhanApiKey, Request>builder()
			.dataHeader(apiKey)
			.dataBody(Request.builder()
				.출금계좌번호(accountNumber)
				.build()).build();

		ShinhanApiDto<ShinhanResponseHeader, Response> balanceDetail = apiClient.getBalanceDetail(
			request);

		if (balanceDetail.getDataHeader().getSuccessCode().equals("1")) {
			throw new RuntimeException(balanceDetail.getDataHeader().getResultCode());
		}

		return apiClient.getBalanceDetail(request).getDataBody().toDto();
	}

	@Override
	public TransactionInfo getAccountTransaction(String accountNumber) {

		ShinhanApiDto<ShinhanApiKey, ShinhanTransactionApi.Request> request = ShinhanApiDto.<ShinhanApiKey, ShinhanTransactionApi.Request>builder()
			.dataHeader(apiKey)
			.dataBody(ShinhanTransactionApi.Request.builder()
				.계좌번호(accountNumber)
				.build())
			.build();

		ShinhanApiDto<ShinhanResponseHeader, ShinhanTransactionApi.Response> transactions = apiClient.getTransactions(
			request);

		if (transactions.getDataHeader().getSuccessCode().equals(1)) {
			throw new RuntimeException(transactions.getDataHeader().getResultCode());
		}

		return transactions.getDataBody().toDto();
	}
}
