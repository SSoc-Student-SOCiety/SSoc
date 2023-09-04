package gwangju.ssafy.backend.global.infra.feign.shinhan.service.impl;

import gwangju.ssafy.backend.global.infra.feign.shinhan.ShinhanApiFeignClient;
import gwangju.ssafy.backend.global.infra.feign.shinhan.dto.ShinhanApiDto;
import gwangju.ssafy.backend.global.infra.feign.shinhan.dto.ShinhanBalanceDetailApi.Request;
import gwangju.ssafy.backend.global.infra.feign.shinhan.dto.ShinhanBalanceDetailApi.Response;
import gwangju.ssafy.backend.global.infra.feign.shinhan.dto.ShinhanResponseHeader;
import gwangju.ssafy.backend.global.infra.feign.shinhan.dto.ShinhanSearchNameApi;
import gwangju.ssafy.backend.global.infra.feign.shinhan.dto.ShinhanTransactionApi;
import gwangju.ssafy.backend.global.infra.feign.shinhan.dto.ShinhanTransferApi;
import gwangju.ssafy.backend.global.infra.feign.shinhan.dto.ShinhanTransferAuthApi;
import gwangju.ssafy.backend.global.infra.feign.shinhan.dto.TransactionInfo;
import gwangju.ssafy.backend.global.infra.feign.shinhan.dto.TransferRequest;
import gwangju.ssafy.backend.global.infra.feign.shinhan.dto.TransferResult;
import gwangju.ssafy.backend.global.infra.feign.shinhan.dto.DepositHolderName;
import gwangju.ssafy.backend.global.infra.feign.shinhan.dto.BalanceDetail;
import gwangju.ssafy.backend.global.infra.feign.shinhan.service.ShinhanBankService;
import gwangju.ssafy.backend.global.infra.feign.shinhan.service.impl.ShinhanApiKey;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
class ShinhanBankServiceImpl implements ShinhanBankService {

	private final ShinhanApiKey apiKey;
	private final ShinhanApiFeignClient apiClient;

	@Override
	public BalanceDetail getBalanceDetail(String accountNumber) {

		ShinhanApiDto<ShinhanApiKey, Request> request = ShinhanApiDto.<ShinhanApiKey, Request>builder()
			.dataHeader(apiKey).dataBody(Request.builder().출금계좌번호(accountNumber).build()).build();

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
			.dataBody(ShinhanTransactionApi.Request.builder().계좌번호(accountNumber).build()).build();

		ShinhanApiDto<ShinhanResponseHeader, ShinhanTransactionApi.Response> transactions = apiClient.getTransactions(
			request);

		if (transactions.getDataHeader().getSuccessCode().equals("1")) {
			throw new RuntimeException(transactions.getDataHeader().getResultCode());
		}

		return transactions.getDataBody().toDto();
	}

	@Override
	public DepositHolderName searchDepositHolderName(String depositBankCode,
		String depositAccountNumber) {

		ShinhanApiDto<ShinhanApiKey, ShinhanSearchNameApi.Request> request = ShinhanApiDto.<ShinhanApiKey, ShinhanSearchNameApi.Request>builder()
			.dataHeader(apiKey).dataBody(
				ShinhanSearchNameApi.Request.builder().입금계좌번호(depositAccountNumber)
					.입금은행코드(depositBankCode).build()).build();

		ShinhanApiDto<ShinhanResponseHeader, ShinhanSearchNameApi.Response> name = apiClient.searchName(
			request);

		if (name.getDataHeader().getSuccessCode().equals("1")) {
			throw new RuntimeException(name.getDataHeader().getResultCode());
		}

		return name.getDataBody().toDto();
	}

	@Override
	public TransferResult transferMoney(TransferRequest transferRequest) {

		ShinhanApiDto<ShinhanApiKey, ShinhanTransferApi.Request> request =
			ShinhanApiDto.<ShinhanApiKey, ShinhanTransferApi.Request>builder()
				.dataHeader(apiKey).dataBody(ShinhanTransferApi.Request.builder()
					.출금계좌번호(transferRequest.getWithdrawalAccountNumber())
					.입금은행코드(transferRequest.getDepositBankCode())
					.입금계좌번호(transferRequest.getDepositAccountNumber())
					.이체금액(transferRequest.getAmount())
					.입금계좌통장메모(transferRequest.getDepositMessage())
					.출금계좌통장메모(transferRequest.getWithdrawalMessage()).build())
				.build();

		ShinhanApiDto<ShinhanResponseHeader, ShinhanTransferApi.Response> transfer =
			apiClient.transferMoney(request);

		if (transfer.getDataHeader().getSuccessCode().equals("1")) {
			throw new RuntimeException(transfer.getDataHeader().getResultCode());
		}

		return transfer.getDataBody().toDto();
	}

	@Override
	public void transferAuth(String bankCode, String accountNumber, String message) {

		ShinhanApiDto<ShinhanApiKey, ShinhanTransferAuthApi.Request> request =
			ShinhanApiDto.<ShinhanApiKey, ShinhanTransferAuthApi.Request>builder()
				.dataHeader(apiKey)
				.dataBody(ShinhanTransferAuthApi.Request.builder()
					.입금은행코드(bankCode)
					.입금계좌번호(accountNumber)
					.입금통장메모(message)
					.build())
				.build();

		ShinhanApiDto<ShinhanResponseHeader, ShinhanTransferAuthApi.Response> response =
			apiClient.transferAuth(request);

		if (response.getDataHeader().getSuccessCode().equals("1")) {
			throw new RuntimeException("1원 이체 실패");
		}
	}
}
