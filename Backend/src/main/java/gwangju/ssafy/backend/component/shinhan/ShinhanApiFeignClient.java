package gwangju.ssafy.backend.component.shinhan;


import gwangju.ssafy.backend.component.shinhan.dto.ShinhanBalanceDetailApi;
import gwangju.ssafy.backend.component.shinhan.dto.ShinhanApiDto;
import gwangju.ssafy.backend.component.shinhan.dto.ShinhanResponseHeader;
import gwangju.ssafy.backend.component.shinhan.dto.ShinhanTransactionApi;
import gwangju.ssafy.backend.config.HeaderConfiguration;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "shinhan", url = "https://shbhack.shinhan.com", configuration = {
	HeaderConfiguration.class})
interface ShinhanApiFeignClient {

	@PostMapping("/v1/account/balance/detail")
	ShinhanApiDto<ShinhanResponseHeader, ShinhanBalanceDetailApi.Response> getBalanceDetail(
		@RequestBody ShinhanApiDto<ShinhanApiKey, ShinhanBalanceDetailApi.Request> request);

	@PostMapping("/v1/search/transaction")
	ShinhanApiDto<ShinhanResponseHeader, ShinhanTransactionApi.Response> getTransactions(
		@RequestBody ShinhanApiDto<ShinhanApiKey, ShinhanTransactionApi.Request> request);

}
