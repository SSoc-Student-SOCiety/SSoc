package gwangju.ssafy.backend.domain.transaction.service;

import gwangju.ssafy.backend.domain.transaction.dto.RegisterGroupAccountRequest;
import gwangju.ssafy.backend.domain.transaction.dto.SendAuthCodeRequest;
import gwangju.ssafy.backend.domain.transaction.dto.UnregisterGroupAccountRequest;

public interface GroupAccountService {

	void sendAuthCode(SendAuthCodeRequest request);

	void registerGroupAccount(RegisterGroupAccountRequest request);

	void unregisterGroupAccount(UnregisterGroupAccountRequest request);


}
