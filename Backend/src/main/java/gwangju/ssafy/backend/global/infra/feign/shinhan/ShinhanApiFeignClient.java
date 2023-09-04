package gwangju.ssafy.backend.global.infra.feign.shinhan;


import gwangju.ssafy.backend.global.infra.feign.shinhan.dto.ShinhanApiDto;
import gwangju.ssafy.backend.global.infra.feign.shinhan.dto.ShinhanBalanceDetailApi.Request;
import gwangju.ssafy.backend.global.infra.feign.shinhan.dto.ShinhanBalanceDetailApi.Response;
import gwangju.ssafy.backend.global.infra.feign.shinhan.dto.ShinhanResponseHeader;
import gwangju.ssafy.backend.global.infra.feign.shinhan.dto.ShinhanSearchNameApi;
import gwangju.ssafy.backend.global.infra.feign.shinhan.dto.ShinhanTransactionApi;
import gwangju.ssafy.backend.global.infra.feign.shinhan.dto.ShinhanTransferApi;
import gwangju.ssafy.backend.global.infra.feign.shinhan.dto.ShinhanTransferAuthApi;
import gwangju.ssafy.backend.global.config.HeaderConfiguration;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "shinhan", url = "https://shbhack.shinhan.com", configuration = {
	HeaderConfiguration.class})
interface ShinhanApiFeignClient {

	@PostMapping("/v1/account/balance/detail")
	ShinhanApiDto<ShinhanResponseHeader, Response> getBalanceDetail(
		@RequestBody ShinhanApiDto<ShinhanApiKey, Request> request);

	@PostMapping("/v1/search/transaction")
	ShinhanApiDto<ShinhanResponseHeader, ShinhanTransactionApi.Response> getTransactions(
		@RequestBody ShinhanApiDto<ShinhanApiKey, ShinhanTransactionApi.Request> request);

	@PostMapping("/v1/search/name")
	ShinhanApiDto<ShinhanResponseHeader, ShinhanSearchNameApi.Response> searchName(
		@RequestBody ShinhanApiDto<ShinhanApiKey, ShinhanSearchNameApi.Request> request);

	@PostMapping("/v1/transfer/krw")
	ShinhanApiDto<ShinhanResponseHeader, ShinhanTransferApi.Response> transferMoney(
		@RequestBody ShinhanApiDto<ShinhanApiKey, ShinhanTransferApi.Request> request);

	@PostMapping("/v1/auth/1transfer")
	ShinhanApiDto<ShinhanResponseHeader, ShinhanTransferAuthApi.Response> transferAuth(
		@RequestBody ShinhanApiDto<ShinhanApiKey, ShinhanTransferAuthApi.Request> request);


}
