package gwangju.ssafy.backend.domain.account.service;

import gwangju.ssafy.backend.domain.account.dto.RegisterGroupAccountRequest;
import gwangju.ssafy.backend.domain.account.dto.SendAuthCodeRequest;
import gwangju.ssafy.backend.domain.account.dto.UnregisterGroupAccountRequest;

public interface GroupAccountService {

	void sendAuthCode(SendAuthCodeRequest request);

	void registerGroupAccount(RegisterGroupAccountRequest request);

	void unregisterGroupAccount(UnregisterGroupAccountRequest request);


}
